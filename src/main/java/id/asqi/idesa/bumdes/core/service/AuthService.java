//package id.asqi.idesa.bumdes.core.service;
//
//import id.asqi.idesa.bumdes.core.http.request.AuthRequest;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//	private final AuthenticationManager authenticationManager;
//	private final EnvService envService;
//	private final UserRepository userRepository;
//	private final PasswordEncoder passwordEncoder;
//
//	public User authenticate (@Valid @RequestBody AuthRequest.Login req) {
//		User user = userRepository.findByUsername(req.getUsername()).orElseThrow(() -> new BadCredentialsException("Bad credentials"));
//		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), req.getPassword()));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		return user;
//	}
//
//	public void setCookie (HttpServletResponse servletResponse, String jwt) {
//		Cookie cookie = new Cookie(envService.cookieKey, jwt);
//		System.out.println("=== cookie ===========");
//		System.out.println(cookie.getName());
//		System.out.println(cookie.getValue());
//		System.out.println("======================");
//		cookie.setPath("/");
//		cookie.setHttpOnly(true);
//		cookie.setSecure(true);
//		cookie.setMaxAge(envService.cookieExpirationInMs);
////        cookie.setDomain("localhost");
//		servletResponse.addCookie(cookie);
//	}
//
//	public void setCookieLogout (HttpServletResponse servletResponse) {
//		Cookie cookie = new Cookie(envService.cookieKey, "");
//		cookie.setPath("/");
//		cookie.setHttpOnly(true);
//		cookie.setSecure(true);
//		cookie.setMaxAge(0);
//		servletResponse.addCookie(cookie);
//	}
//
//
////	public void resetPassword (AuthRequest.ResetPassword req, User userBjb) {
////		if (! pbd2Hasher.checkPassword(req.getPasswordLama(), userBjb.getPassword())) {
////			throw new InvalidOperationException("password lama tidak sesuai");
////		}
////
////		if (! req.getPasswordBaru().equals(req.getKonfirmasiPasswordBaru())) {
////			throw new InvalidOperationException("password baru dan konfirmasi password berbeda");
////		}
////
////		String newHashedPassword = pbd2Hasher.hash(req.getPasswordBaru());
////		userBjb.setPassword(newHashedPassword);
////		userRepository.save(userBjb);
////	}
//
//
//	//
////	 public ResponseEntity<Response<AuthenticationResponse>> register(
////					 User user
////	 ) {
////			if (userBjbRepository.existsByUsername(user.getUsername())) return CommonResponse.badRequest("username taken!");
////			if (userBjbRepository.existsByEmail(user.getEmail())) return CommonResponse.badRequest("email taken!");
////
////
////			userService.save(user);
////			return CommonResponse.success();
////
//////			AuthenticationResponse authenticationResponse = this.authenticate(user.getUsername(), user.getPassword());
//////			return CommonResponse.authResponse(authenticationResponse);
////	 }
//
//}