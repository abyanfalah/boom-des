package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.SearchBasicFiltersRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.http.request.KategoriKomoditasRequest;
import id.asqi.idesa.bumdes.model.KategoriKomoditas;
import id.asqi.idesa.bumdes.service.KategoriKomoditasService;
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
@RequestMapping("kategori-komoditas")
@RequiredArgsConstructor
public class KategoriKomoditasController {
	private final KategoriKomoditasService kategoriKomoditasService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<KategoriKomoditas>>> getAll(
			@RequestBody @Valid SearchPaginationRequest req
	){
		Page<KategoriKomoditas> page = kategoriKomoditasService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping("list")
	public ResponseEntity<Response<List<KategoriKomoditas>>> getList(
			@RequestBody @Valid SearchBasicFiltersRequest req
	){
		List<KategoriKomoditas> data = kategoriKomoditasService.getList(req);
		return CommonResponse.data(data);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(
			@RequestBody @Valid KategoriKomoditasRequest.Create req
	) throws Exception {
		kategoriKomoditasService.create(req);
		return CommonResponse.created(KategoriKomoditas.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(
			@RequestBody @Valid KategoriKomoditasRequest.Update req
	){
		kategoriKomoditasService.update(req);
		return CommonResponse.updated(KategoriKomoditas.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(
			@RequestBody @Valid SetDeleteStatusRequest req
	){
		kategoriKomoditasService.softDelete(req);
		return CommonResponse.softDeleted(KategoriKomoditas.class);
	}
}