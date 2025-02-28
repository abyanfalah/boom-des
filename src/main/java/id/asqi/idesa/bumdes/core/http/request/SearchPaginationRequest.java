package id.asqi.idesa.bumdes.core.http.request;

import id.asqi.idesa.bumdes.core.component.MyPagination;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class SearchPaginationRequest extends PaginationRequest {
	String search = "";

	public String getSearch () {
		return this.search == null ? "" : this.search.trim();
	}

	public Pageable getPagination(){
		return MyPagination.paginate(this);
	}
}