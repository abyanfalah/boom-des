package id.asqi.idesa.bumdes.core.http.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


public class PendudukRequest {

	@Getter
	@Setter
	public static class Create {
		@NotBlank
		@Size(max = 16)
		private String nik;

		@NotBlank
		@Size(max = 255)
		private String nama;

		@NotNull
		private Long alamatDesaId;
	}

	@Getter
	@Setter
	public static class Update extends IdNumberRequest {
		@NotBlank
		@Size(max = 16)
		private String nik;

		@NotBlank
		@Size(max = 255)
		private String nama;

		@NotNull
		private Long alamatDesaId;
	}

	@Getter
	@Setter
	public static class Filter extends SearchPaginationRequest {
	}
}