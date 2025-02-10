package id.asqi.idesa.bumdes.core.http.request;

import lombok.Getter;

@Getter
public class SearchPaginationRequest extends PaginationRequest {
	String search = "";

	public String getSearch () {
		return this.search == null ? "" : this.search.trim();
	}
}