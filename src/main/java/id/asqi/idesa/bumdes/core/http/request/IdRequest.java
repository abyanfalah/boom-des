package id.asqi.idesa.bumdes.core.http.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class IdRequest {
	 @NotBlank
	 String id;
}