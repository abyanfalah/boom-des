package id.asqi.idesa.bumdes.http.request;

import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class KategoriPostinganForumBumdesRequest {

	@Getter
	@Setter
	public static class Create {
		@NotBlank
		private String nama;
	}

	@Getter
	@Setter
	public static class Update extends IdNumberRequest {
		@NotBlank
		private String nama;
	}
}