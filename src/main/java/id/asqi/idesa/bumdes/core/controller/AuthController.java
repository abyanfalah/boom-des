package id.asqi.idesa.bumdes.core.controller;

import id.asqi.idesa.bumdes.core.auth.UserDetailsImpl;
import id.asqi.idesa.bumdes.core.config.security.jwt.JwtUtils;
import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.AuthRequest;
import id.asqi.idesa.bumdes.core.http.response.AuthResponse;
import id.asqi.idesa.bumdes.core.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	private final JwtUtils jwtUtils;

	@PostMapping("login")
	public ResponseEntity<Response<AuthResponse>> login(
			@RequestBody @Valid AuthRequest.Login req,
			HttpServletResponse servletResponse
	) {
		UserDetailsImpl userBumdes = authService.authenticate(req);

		String jwt = jwtUtils.generateJwt(userBumdes);
		AuthResponse response = AuthResponse.builder()
				.id(userBumdes.getId())
				.username(userBumdes.getUsername())
				.token(jwt)
				.build();

		authService.setCookie(servletResponse, jwt);
		return CommonResponse.authResponse(response);
	}

	@PostMapping("logout")
	public ResponseEntity<Response<Void>> logout(
			HttpServletResponse servletResponse
	) {
		authService.setCookieLogout(servletResponse);
		return CommonResponse.success("Logout Success");
	}
}