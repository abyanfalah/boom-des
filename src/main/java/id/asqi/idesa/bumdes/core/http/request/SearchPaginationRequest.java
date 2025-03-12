package id.asqi.idesa.bumdes.core.http.request;

import id.asqi.idesa.bumdes.core.component.MyPagination;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class SearchPaginationRequest extends PaginationRequest {
	private String search = "";

	private Boolean isAktif;

	private Boolean isIncludeDeleted;

	public Boolean getIsIncludeDeleted(){
		return isIncludeDeleted != null && isIncludeDeleted;
	}

	public String getSearch () {
		return this.search == null ? "" : this.search.trim();
	}

	public Pageable getPagination(){
		return MyPagination.paginate(this);
	}
}