package id.asqi.idesa.bumdes.core.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class AuthResponse {
	private String type = "Bearer";
	private Long id;
	private String token;
	private String username;
	private Short roles;
	private String roleString;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer isActive;
}