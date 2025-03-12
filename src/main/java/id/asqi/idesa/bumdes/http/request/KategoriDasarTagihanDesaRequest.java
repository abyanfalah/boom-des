package id.asqi.idesa.bumdes.http.request;

import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class KategoriDasarTagihanDesaRequest {

	@Getter
	@Setter
	public static class Filter extends SearchPaginationRequest {
		private Boolean isPajak;
	}

	@Getter
	@Setter
	public static class Create {
		@NotBlank
		private String nama;
		@NotNull
		private Short siklusBayar;
		@NotNull
		private Boolean isPajak;
		@NotNull
		private Boolean isAktif;
	}

	@Getter
	@Setter
	public static class Update extends IdNumberRequest {
		@NotBlank
		private String nama;
		@NotNull
		private Short siklusBayar;
		@NotNull
		private Boolean isPajak;
		@NotNull
		private Boolean isAktif;
	}
}