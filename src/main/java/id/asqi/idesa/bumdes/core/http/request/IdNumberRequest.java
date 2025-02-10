package id.asqi.idesa.bumdes.core.http.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IdNumberRequest {
	 @NotNull
	 private Long id;
}