package id.asqi.idesa.bumdes.core.http.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SetStatusRequest extends IdNumberRequest{
	@NotNull
	private Boolean isAktif;
}