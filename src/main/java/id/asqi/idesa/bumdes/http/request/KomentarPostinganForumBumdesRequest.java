package id.asqi.idesa.bumdes.http.request;

import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


public class KomentarPostinganForumBumdesRequest {

	@Getter
	@Setter
	public static class Create {
		@NotNull
		private Long postinganForumId;
		private Long parentKomentarId;
		@NotBlank
		private String isi;
	}

	@Getter
	@Setter
	public static class Update extends IdNumberRequest {
		@NotBlank
		private String isi;
	}

	@Getter
	@Setter
	public static class Filter extends SearchPaginationRequest {
		@NotNull
		private Long postinganForumId;
		private Long parentKomentarId;
	}
}