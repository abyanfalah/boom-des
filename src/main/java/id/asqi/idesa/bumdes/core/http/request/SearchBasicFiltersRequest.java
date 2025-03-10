package id.asqi.idesa.bumdes.core.http.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBasicFiltersRequest extends BasicFiltersRequest {
	private String search = "";

	public String getSearch () {
		return this.search == null ? "" : this.search.trim();
	}

}