package id.asqi.idesa.bumdes.core.service;

import id.asqi.idesa.bumdes.core.auth.UserDetailsImpl;
import id.asqi.idesa.bumdes.core.http.request.AuthRequest;
import id.asqi.idesa.bumdes.model.UserBumdes;
import id.asqi.idesa.bumdes.repository.UserBumdesRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final AuthenticationManager authenticationManager;
	private final EnvService envService;
	private final UserBumdesRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserDetailsImpl authenticate (AuthRequest.Login req) {
		UserBumdes user = userRepository.findByUsername(req.getUsername()).orElseThrow(() -> new BadCredentialsException("Invalid Username or Password"));
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), req.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return UserDetailsImpl.build(user);
	}

	public void setCookie (HttpServletResponse servletResponse, String jwt) {
		Cookie cookie = new Cookie(envService.cookieKey, jwt);
		System.out.println("=== cookie ===========");
		System.out.println(cookie.getName());
		System.out.println(cookie.getValue());
		System.out.println("=== cookie ===========");
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setSecure(envService.isProd());
		cookie.setMaxAge(envService.cookieExpirationInMs);
//        cookie.setDomain("localhost");
		servletResponse.addCookie(cookie);
	}

	public void setCookieLogout (HttpServletResponse servletResponse) {
		Cookie cookie = new Cookie(envService.cookieKey, "");
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setSecure(envService.isProd());
		cookie.setMaxAge(0);
		servletResponse.addCookie(cookie);
	}
}