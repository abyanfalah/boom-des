package id.asqi.idesa.bumdes.core.http.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetDeleteStatusRequest extends IdNumberRequest{
	@NotNull
	private Boolean isDeleted = true;
}