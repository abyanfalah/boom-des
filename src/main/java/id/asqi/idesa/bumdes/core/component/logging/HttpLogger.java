package id.asqi.idesa.bumdes.core.component.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Component
@Slf4j
public class HttpLogger extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response,
	                                 FilterChain filterChain) throws ServletException, IOException {

		// Check if it's an authentication endpoint
		if (request.getRequestURI().matches("/api/auth/.+")) {
			filterChain.doFilter(request, response);
			return;
		}

		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

		boolean isMultipartFormData = wrappedRequest.getContentType().contains(MediaType.MULTIPART_FORM_DATA_VALUE);
		if (isMultipartFormData) {
			this.logFormDataRequest(wrappedRequest, wrappedResponse, filterChain);
		} else {
			this.logJsonRequest(wrappedRequest, wrappedResponse, filterChain);
		}

		wrappedResponse.copyBodyToResponse();
	}

	private void logFormDataRequest (ContentCachingRequestWrapper wrappedRequest, ContentCachingResponseWrapper wrappedResponse, FilterChain filterChain) throws ServletException, IOException {
		long start = System.currentTimeMillis();

		try {
			filterChain.doFilter(wrappedRequest, wrappedResponse);
		} finally {
			long elapsed = System.currentTimeMillis() - start;
			String username = "[unavailable]", ip, requestBody, responseBody;
			ip = wrappedRequest.getRemoteAddr();
			Collection<Part> parts = wrappedRequest.getParts();
			requestBody = this.compileLogMessage(parts);
			responseBody = new String(wrappedResponse.getContentAsByteArray());
			if (wrappedRequest.getUserPrincipal() != null) {
				username = wrappedRequest.getUserPrincipal().getName();
			}
			writeLog(wrappedRequest.getMethod(), wrappedRequest.getRequestURI(), elapsed, username, ip, requestBody, responseBody);
		}
	}


	private void logJsonRequest (ContentCachingRequestWrapper wrappedRequest, ContentCachingResponseWrapper wrappedResponse, FilterChain filterChain) throws ServletException, IOException {
		long start = System.currentTimeMillis();
		try {
			filterChain.doFilter(wrappedRequest, wrappedResponse);
		} finally {
			long elapsed = System.currentTimeMillis() - start;
			String username = "[unavailable]", ip, requestBody, responseBody;
			ip = wrappedRequest.getRemoteAddr();
			requestBody = new String(wrappedRequest.getContentAsByteArray());
			responseBody = new String(wrappedResponse.getContentAsByteArray());
			if (wrappedRequest.getUserPrincipal() != null) {
				username = wrappedRequest.getUserPrincipal().getName();
			}

			writeLog(wrappedRequest.getMethod(), wrappedRequest.getRequestURI(), elapsed, username, ip, requestBody, responseBody);
		}
	}

	private String compileLogMessage (Collection<Part> parts) {
		StringBuilder logMessage = new StringBuilder();

		for (Part part : parts) {
			String partName = part.getName();
			String partValue = null;


			boolean isText = part.getContentType() == null;
			if (isText) {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), StandardCharsets.UTF_8))) {
					StringBuilder partStringBuilder = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						partStringBuilder.append(line);
					}
					partValue = partStringBuilder.toString();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				partValue = "[binary file]";
			}

			logMessage.append(partName).append(" : ").append(partValue).append(", ");
		}
		String logString = logMessage.toString();
		return logString.substring(0, logString.length() - 2);
	}

	private void writeLog(String method, String uri, long elapsed, String username, String ip, String requestBody, String responseBody){
		log.info("""

								URI          : [{}] {} - {} ms
								Username     : {}
								IP           : {}
								RequestBody  : {}
								ResponseBody : {}
							--------------------------------------------------------------------------------------------------------------------------------
								""",
				method,
				uri,
				elapsed,
				username,
				ip,
				requestBody,
				responseBody
		);
	}
}