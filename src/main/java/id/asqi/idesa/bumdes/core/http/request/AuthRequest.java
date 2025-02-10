package id.asqi.idesa.bumdes.core.http.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class AuthRequest {
	@Getter
	public static class Login {
		@NotBlank
		private String username;
		@NotBlank
		private String password;
	}

	@Getter
	public static class Register {
		@NotBlank
		private String username;
		@NotBlank
		private String email;
		@NotBlank
		private String password;
	}

}