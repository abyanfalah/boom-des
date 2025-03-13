package id.asqi.idesa.bumdes.controller;

import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.SearchBasicFiltersRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.core.http.request.ToggleRequest;
import id.asqi.idesa.bumdes.http.request.KategoriTagihanDesaRequest;
import id.asqi.idesa.bumdes.model.KategoriPostinganForumBumdes;
import id.asqi.idesa.bumdes.model.KategoriTagihanDesa;
import id.asqi.idesa.bumdes.service.KategoriTagihanDesaService;
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
@RequestMapping("kategori-tagihan-desa")
@RequiredArgsConstructor
public class KategoriTagihanDesaController {

	private final KategoriTagihanDesaService kategoriTagihanDesaService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<KategoriTagihanDesa>>> getAll (@RequestBody @Valid SearchPaginationRequest req) {
		Page<KategoriTagihanDesa> page = kategoriTagihanDesaService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping("list")
	public ResponseEntity<Response<List<KategoriTagihanDesa>>> list (@RequestBody @Valid SearchBasicFiltersRequest req) {
		List<KategoriTagihanDesa> result = kategoriTagihanDesaService.getAll(req);
		return CommonResponse.data(result);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create (@RequestBody @Valid KategoriTagihanDesaRequest.Create req) {
		kategoriTagihanDesaService.create(req);
		return CommonResponse.created(KategoriTagihanDesa.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update (@RequestBody @Valid KategoriTagihanDesaRequest.Update req) {
		kategoriTagihanDesaService.update(req);
		return CommonResponse.updated(KategoriTagihanDesa.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete (@RequestBody @Valid SetDeleteStatusRequest req) {
		kategoriTagihanDesaService.delete(req);
		return CommonResponse.toggledSoftDelete(KategoriTagihanDesa.class, req);
	}

	@PostMapping("toggle-status")
	public ResponseEntity<Response<Void>> toggleStatus (@RequestBody @Valid ToggleRequest.AktifStatus req) {
		kategoriTagihanDesaService.toggleStatus(req);
		return CommonResponse.updated(KategoriTagihanDesa.class);
	}
}