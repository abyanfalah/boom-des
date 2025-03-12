package id.asqi.idesa.bumdes.core.http.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class ToggleRequest {
	@Getter @Setter
	public static class AktifStatus extends IdNumberRequest{
		@NotNull
		private Boolean isAktif;
	}

	@Getter @Setter
	public static class ActiveStatus extends IdNumberRequest{
		@NotNull
		private Boolean isActive;
	}

	@Getter @Setter
	public static class DeletedStatus extends IdNumberRequest{
		@NotNull
		private Boolean isDeleted;
	}
}