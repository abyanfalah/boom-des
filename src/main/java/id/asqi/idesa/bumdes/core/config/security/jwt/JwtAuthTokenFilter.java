package id.asqi.idesa.bumdes.core.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.asqi.idesa.bumdes.core.auth.UserDetailsImpl;
import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.service.EnvService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthTokenFilter extends OncePerRequestFilter {
	private final JwtUtils jwtUtils;
	private final JwtUserDetailService jwtUserDetailService;
	private final EnvService envService;

	private boolean isAuthEndpoint (String uri) {
		return uri.matches("/auth/.+");
	}

	@Override
	protected void doFilterInternal (
			HttpServletRequest request,
			@NotNull HttpServletResponse response,
			@NotNull FilterChain filterChain)
			throws ServletException, IOException {

		if (isAuthEndpoint(request.getRequestURI())) {
			filterChain.doFilter(request, response);
			return;
		}

		String username;
		String jwt;

		Cookie[] cookiesArray = request.getCookies();
		if (cookiesArray == null) {
			jwt = this.getJwtFromHeader(request);
		} else {
			Optional<Cookie> matchingCookie = Optional.of(cookiesArray)
					.stream()
					.flatMap(Arrays::stream)
					.filter(c -> c.getName().equals(envService.cookieKey))
					.findFirst();
			if (matchingCookie.isEmpty()) {
				jwt = this.getJwtFromHeader(request);
			} else {
				jwt = matchingCookie.get().getValue();
			}
		}

		if (jwt == null || jwt.isBlank()) {
			filterChain.doFilter(request, response);
			return;
		}

		try {
			username = jwtUtils.extractUsername(jwt);
		} catch (MalformedJwtException | IllegalArgumentException | UnsupportedJwtException | ExpiredJwtException e) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			Response<?> data = CommonResponse.unauthenticated(e.getClass().getSimpleName() + " : " + e.getMessage()).getBody();
			ObjectMapper mapper = new ObjectMapper();
			OutputStream out = response.getOutputStream();
			mapper.writeValue(out, data);
			return;
		}

		if (username == null || username.isBlank()) {
			filterChain.doFilter(request, response);
			return;
		}

		UserDetailsImpl userDetails = jwtUserDetailService.loadUserByUsername(username);

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails,
				null,
				userDetails.getAuthorities()
		);

		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	}

	public String getJwtFromHeader (HttpServletRequest request) {
		final String authHeader = request.getHeader("Authorization");
		boolean invalidAuthHeader =
				authHeader == null
						|| authHeader.isBlank()
						|| ! authHeader.startsWith("Bearer ");

		if (invalidAuthHeader) {
			return null;
		}

		return authHeader.substring(7);
	}

}