package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.SearchBasicFiltersRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.http.request.KategoriPostinganForumBumdesRequest;
import id.asqi.idesa.bumdes.model.KategoriPostinganForumBumdes;
import id.asqi.idesa.bumdes.service.KategoriPostinganForumBumdesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("kategori-postingan-forum-bumdes")
@RequiredArgsConstructor
public class KategoriPostinganForumBumdesController {

	private final KategoriPostinganForumBumdesService kategoriPostinganForumBumdesService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<KategoriPostinganForumBumdes>>> getAll(
			@RequestBody @Valid SearchPaginationRequest req
	) {
		Page<KategoriPostinganForumBumdes> page = kategoriPostinganForumBumdesService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping("list")
	public ResponseEntity<Response<List<KategoriPostinganForumBumdes>>> getList(
			@RequestBody @Valid SearchBasicFiltersRequest req
	) {
		List<KategoriPostinganForumBumdes> data = kategoriPostinganForumBumdesService.getList(req);
		return CommonResponse.data(data);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(
			@RequestBody @Valid KategoriPostinganForumBumdesRequest.Create req
	) throws Exception {
		kategoriPostinganForumBumdesService.create(req);
		return CommonResponse.created(KategoriPostinganForumBumdes.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(
			@RequestBody @Valid KategoriPostinganForumBumdesRequest.Update req
	) {
		kategoriPostinganForumBumdesService.update(req);
		return CommonResponse.updated(KategoriPostinganForumBumdes.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(
			@RequestBody @Valid SetDeleteStatusRequest req
	) {
		kategoriPostinganForumBumdesService.softDelete(req);
		return CommonResponse.toggledSoftDelete(KategoriPostinganForumBumdes.class, req);
	}
}