package id.asqi.idesa.bumdes.http.request;


import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class RekeningUserBumdesRequest {

	@Getter
	@Setter
	public static class Create{
		@NotBlank
		private String namaBank;

		@NotBlank
		private String atasNama;

		@NotBlank
		private String nomorRekening;
	}

	@Getter
	@Setter
	public static class Update extends IdNumberRequest {
		@NotBlank
		private String namaBank;

		@NotBlank
		private String atasNama;

		@NotBlank
		private String nomorRekening;

		@NotNull
		private Boolean isRekeningUtama;
	}
}