package id.asqi.idesa.bumdes.core.http.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter @Setter
public class PaginationRequest extends BasicFiltersRequest {
	 @Min(0)
	 int page;

	 @Min(1)
	 int size;
	 Sort.Direction direction;
}