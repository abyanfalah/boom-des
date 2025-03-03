package id.asqi.idesa.bumdes.http.request;


import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class InformasiRequest {

	@Getter
	@Setter
	public static class Create{
		@NotBlank
		private String judul;

		@NotBlank
		private String isi;

		private List<GambarInformasiReq> gambar;
		private List<MultipartFile> dokumen;

		@Getter
		@Setter
		public static class GambarInformasiReq {
			@NotNull
			private MultipartFile file;

			@NotNull
			private Boolean isCover;
		}
	}

	@Getter
	@Setter
	public static class Update extends IdNumberRequest{
		@NotBlank
		private String judul;

		@NotBlank
		private String isi;

		private List<GambarInformasiReq> gambar;
		private List<DokumenInformasiReq> dokumen;

		@Getter
		@Setter
		public static class GambarInformasiReq extends IdNumberRequest {
			private MultipartFile file;

			private Boolean isCover;
		}

		@Getter
		@Setter
		public static class DokumenInformasiReq extends IdNumberRequest {
			private MultipartFile file;
		}
	}
}