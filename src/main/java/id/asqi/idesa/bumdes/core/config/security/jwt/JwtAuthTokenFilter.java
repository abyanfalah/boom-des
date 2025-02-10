package id.asqi.idesa.bumdes.core.config.security.jwt;

import id.asqi.idesa.bumdes.core.service.EnvService;
import id.asqi.idesa.bumdes.model.UserBumdes;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthTokenFilter extends OncePerRequestFilter {
	private final JwtUtils jwtUtils;
	private final JwtUserDetailService jwtUserDetailService;
	private final EnvService envService;

	private final List<String> authEndpoints = List.of(
			"/api/auth/login",
			"/api/auth/logout"
	);

	private boolean isAuthEndpoint (HttpServletRequest request) {
		return authEndpoints.contains(
				request.getRequestURI()
		);
	}

	@Override
	protected void doFilterInternal (
			@NotNull HttpServletRequest request,
			@NotNull HttpServletResponse response,
			@NotNull FilterChain filterChain)
			throws ServletException, IOException {

		/*disabled. figured it out.
		 * now using just WebSecurityConfig
		 * for the business
		 * */
//		/*bypass auth check*/
//		if (isAuthEndpoint(request)) {
//			filterChain.doFilter(request, response);
//			return;
//		}

		String username;
		String jwt;

		/*find the right cookie or get it from header... or just kick the user.*/
		Cookie[] cookiesArray = request.getCookies();
		if (cookiesArray == null) {
			jwt = this.getJwtFromHeader(request);
		} else {
			Optional<Cookie> matchingCookie = Optional.of(cookiesArray).stream().flatMap(Arrays::stream)
					.filter(c -> c.getName().equals(envService.cookieKey))
					.findFirst();
			if (matchingCookie.isEmpty()) {
				jwt = this.getJwtFromHeader(request);
			} else {
				jwt = matchingCookie.get().getValue();
			}
		}

		if (jwt == null) {
			filterChain.doFilter(request, response);
			return;
		}

		username = jwtUtils.extractUsername(jwt);
		if (username == null || username.isBlank() || SecurityContextHolder.getContext().getAuthentication() != null) {
			filterChain.doFilter(request, response);
			return;
		}

		UserDetails user = jwtUserDetailService.loadUserByUsername(username);

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user,
				null,
				null
		);

		authenticationToken.setDetails(
				new WebAuthenticationDetailsSource().buildDetails(request)
		);

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