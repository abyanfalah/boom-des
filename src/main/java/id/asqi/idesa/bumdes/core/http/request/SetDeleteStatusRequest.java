package id.asqi.idesa.bumdes.core.http.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SetDeleteStatusRequest extends IdNumberRequest{
	private Boolean isDeleted = true;

	/*will be used for had deletion*/
	private Boolean isHardAf = false;

	public Boolean getIsDeleted(){
		return isDeleted == null || isDeleted;
	}
	public LocalDateTime getIsDeletedAt(){
		return this.getIsDeleted() ? LocalDateTime.now() : null;
	}
}