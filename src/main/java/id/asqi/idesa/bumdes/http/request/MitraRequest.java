package id.asqi.idesa.bumdes.http.request;

import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class MitraRequest {
	@Getter
	@Setter
	public static class Create {
		@NotNull
		private Long pendudukId;

		@NotNull
		private Long jenisMitraId;

		@NotNull
		private Long jabatanMitraId;

		@NotNull
		private Boolean isAktif;
	}

	@Getter
	@Setter
	public static class Update extends IdNumberRequest {
		@NotNull
		private Long jenisMitraId;
		@NotNull
		private Boolean isAktif;
	}
}