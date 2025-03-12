package id.asqi.idesa.bumdes.controller;

import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchBasicFiltersRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.http.request.ToggleRequest;
import id.asqi.idesa.bumdes.http.request.KategoriDasarTagihanDesaRequest;
import id.asqi.idesa.bumdes.model.KategoriDasarTagihanDesa;
import id.asqi.idesa.bumdes.service.KategoriDasarTagihanDesaService;
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
@RequestMapping("kategori-dasar-tagihan-desa")
@RequiredArgsConstructor
public class KategoriDasarTagihanDesaController {

	private final KategoriDasarTagihanDesaService kategoriDasarTagihanDesaService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<KategoriDasarTagihanDesa>>> getAll(
			@RequestBody @Valid KategoriDasarTagihanDesaRequest.Filter req
	) {
		Page<KategoriDasarTagihanDesa> page = kategoriDasarTagihanDesaService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping("list")
	public ResponseEntity<Response<List<KategoriDasarTagihanDesa>>> getAll(
			@RequestBody @Valid SearchBasicFiltersRequest req
	) {
		List<KategoriDasarTagihanDesa> list = kategoriDasarTagihanDesaService.getAll(req);
		return CommonResponse.data(list);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(@RequestBody @Valid KategoriDasarTagihanDesaRequest.Create req) {
		kategoriDasarTagihanDesaService.create(req);
		return CommonResponse.created(KategoriDasarTagihanDesa.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(@RequestBody @Valid KategoriDasarTagihanDesaRequest.Update req) {
		kategoriDasarTagihanDesaService.update(req);
		return CommonResponse.updated(KategoriDasarTagihanDesa.class);
	}

	@PostMapping("toggle-status")
	public ResponseEntity<Response<Void>> toggleStatus(@RequestBody @Valid ToggleRequest.AktifStatus req) {
		kategoriDasarTagihanDesaService.toggleStatus(req);
		return CommonResponse.updated(KategoriDasarTagihanDesa.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(IdNumberRequest req) {
		kategoriDasarTagihanDesaService.delete(req);
		return CommonResponse.deleted(KategoriDasarTagihanDesa.class);
	}
}