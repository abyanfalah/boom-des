package id.asqi.idesa.bumdes.core.http.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicFiltersRequest {
	private Boolean isAktif;

	private Boolean isIncludeDeleted;
}