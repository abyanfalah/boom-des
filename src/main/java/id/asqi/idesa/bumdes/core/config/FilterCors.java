package id.asqi.idesa.bumdes.core.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FilterCors implements Filter {
	public FilterCors () {
	}

	private final List<String> allowedOrigins = List.of(
			"http://127.0.0.1:*",
			"http://127.0.0.1:3000"
	);

	public void doFilter (ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		if (req instanceof HttpServletRequest request && res instanceof HttpServletResponse response) {


			String origin = request.getHeader("Origin");
//			response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Vary", "Origin");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods", "ACL, CANCELUPLOAD, CHECKING, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, OPTIONS, POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");
			if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				chain.doFilter(req, res);
			}
		}
	}

	@Override
	public void init (FilterConfig filterConfig) {
	}

	@Override
	public void destroy () {
	}
}