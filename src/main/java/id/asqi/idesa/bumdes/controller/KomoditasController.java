package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchBasicFiltersRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.http.request.KomoditasRequest;
import id.asqi.idesa.bumdes.model.Komoditas;
import id.asqi.idesa.bumdes.service.KomoditasService;
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
@RequestMapping("komoditas")
@RequiredArgsConstructor
public class KomoditasController {
	private final KomoditasService KomoditasService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<Komoditas>>> getAll(
			@RequestBody @Valid SearchPaginationRequest req
	){
		Page<Komoditas> page = KomoditasService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(
			@RequestBody @Valid KomoditasRequest.Create req
	) throws Exception {
		KomoditasService.create(req);
		return CommonResponse.created(Komoditas.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(
			@RequestBody @Valid KomoditasRequest.Update req
	){
		KomoditasService.update(req);
		return CommonResponse.updated(Komoditas.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(
			@RequestBody @Valid SetDeleteStatusRequest req
	){
		KomoditasService.softDelete(req);
		return CommonResponse.toggledSoftDelete(Komoditas.class, req);
	}
}