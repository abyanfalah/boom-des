package id.asqi.idesa.bumdes.core.http.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class AuthRequest {
	@Getter
	@Setter
	public static class Login {
		@NotBlank
		private String username;
		@NotBlank
		private String password;
	}

	@Getter
	@Setter
	public static class Register {
		@NotBlank
		private String username;
		@NotBlank
		private String email;
		@NotBlank
		private String password;
	}

}