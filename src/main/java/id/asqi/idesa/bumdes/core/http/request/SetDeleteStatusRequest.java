package id.asqi.idesa.bumdes.core.http.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetDeleteStatusRequest extends IdNumberRequest{
	private Boolean isDeleted = true;

	public Boolean getIsDeleted(){
		return isDeleted == null || isDeleted;
	}
}