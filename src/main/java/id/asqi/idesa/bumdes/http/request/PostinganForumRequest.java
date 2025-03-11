package id.asqi.idesa.bumdes.http.request;

import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PostinganForumRequest {

	@Getter
	@Setter
	public static class Create {
		@NotBlank
		private String judul;
		@NotBlank
		private String isi;
		@NotNull
		private Long kategoriId;

		private List<MultipartFile> gambar;
	}

	@Getter
	@Setter
	public static class Update extends IdNumberRequest {
		@NotBlank
		private String judul;
		@NotBlank
		private String isi;
		@NotNull
		private Long kategoriId;
		private List<MultipartFile> gambar;

	}
}