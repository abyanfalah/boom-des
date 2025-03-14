package id.asqi.idesa.bumdes.http.request;

import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MappingTagihanPendudukRequest {

	@Getter
	@Setter
	public static class Create {
		@NotNull
		private Long pendudukId;
		@NotNull
		private List<Long> kategoriTagihanDesaIds;
	}

	@Getter
	@Setter
	public static class Update extends IdNumberRequest {
		@NotNull @NotEmpty
		private List<Long> kategoriTagihanDesaIds;
	}

	@Getter
	@Setter
	public static class Filter extends SearchPaginationRequest {

	}
}