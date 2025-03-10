package id.asqi.idesa.bumdes.core.http.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IdNumberRequest {
	 @NotNull
	 private Long id;


	 /*for batch update operation where creation and updation
	 * can happen in one request.
	 * use this to check if it's new or updated existing entity.
	 *
	 * you decide the logic
	 * */
	 public Boolean isNew(){
		 return id == 0L;
	 }
}