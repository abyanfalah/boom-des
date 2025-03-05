package id.asqi.idesa.bumdes.http.request;


import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class InformasiTokoDesaRequest {

	@Getter
	@Setter
	public static class Create {
		@NotBlank
		private String nama;

		@NotBlank
		private String noTelpon;

		private MultipartFile fotoProfil;

		@NotNull
		@NotEmpty
		private List<Long> listMetodePengirimanId;

		@NotBlank
		private String alamatLengkap;

		@NotBlank
		private String latitude;

		@NotBlank
		private String longitude;
	}

	@Getter
	@Setter
	public static class Update {
		@NotBlank
		private String nama;

		@NotBlank
		private String noTelpon;

		private MultipartFile fotoProfil;

		@NotNull
		@NotEmpty
		private List<Long> listMetodePengirimanId;

		@NotBlank
		private String alamatLengkap;

		@NotBlank
		private String latitude;

		@NotBlank
		private String longitude;
	}

}