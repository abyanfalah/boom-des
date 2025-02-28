package id.asqi.idesa.bumdes.core.component;

import id.asqi.idesa.bumdes.core.http.request.PaginationRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;



public class MyPagination {
	private static final int firstPage = 0;


	public static Pageable paginateAndSortByCreatedAt (int page, int size) {
		if (page < firstPage) page = firstPage;
		Sort sort = Sort.by(Sort.Direction.ASC, "createdAt");
		return PageRequest.of(page, size, sort);
	}

	public static Pageable paginateAndSortByCreatedAtDesc (int page, int size) {
		if (page < firstPage) page = firstPage;
		Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
		return PageRequest.of(page, size, sort);
	}

	public static Pageable paginateAndSortByCreatedAtDesc (PaginationRequest req) {
		if (req.getPage() < firstPage) req.setPage(0);
		Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
		return PageRequest.of(req.getPage(), req.getSize(), sort);
	}

	public static Pageable paginate (int page, int size) {
		if (page < firstPage) page = firstPage;
		return PageRequest.of(page, size);
	}

	public static Pageable paginate (int page, int size, String columnSort) {
		if (page < firstPage) page = firstPage;
		Sort sort = Sort.by(Sort.Direction.ASC, columnSort);
		return PageRequest.of(page, size, sort);
	}

	public static Pageable paginate (int page, int size, String columnSort, Sort.Direction sortDirection) {
		if (page < firstPage) page = firstPage;
		Sort sort = Sort.by(sortDirection, columnSort);
		return PageRequest.of(page, size, sort);
	}

	public static Pageable paginate (PaginationRequest req, String columnSort, Sort.Direction sortDirection) {
		if (req.getPage() < firstPage) req.setPage(0);
		Sort sort = Sort.by(sortDirection, columnSort);
		return PageRequest.of(req.getPage(), req.getSize(), sort);
	}

	public static Pageable paginate (PaginationRequest req, String columnSort) {
		if (req.getPage() < firstPage) req.setPage(0);
		Sort sort = Sort.by(req.getDirection(), columnSort);
		return PageRequest.of(req.getPage(), req.getSize(), sort);
	}


	public static Pageable paginate (PaginationRequest req) {
		if (req.getPage() < firstPage) req.setPage(0);
		return PageRequest.of(req.getPage(), req.getSize());
	}


}