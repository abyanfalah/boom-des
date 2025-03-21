package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
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

@RestController
@RequestMapping("komoditas")
@RequiredArgsConstructor
public class KomoditasController {
	private final KomoditasService komoditasService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<Komoditas>>> getAll(
			@RequestBody @Valid SearchPaginationRequest req
	){
		Page<Komoditas> page = komoditasService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(
			@RequestBody @Valid KomoditasRequest.Create req
	) throws Exception {
		komoditasService.create(req);
		return CommonResponse.created(Komoditas.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(
			@RequestBody @Valid KomoditasRequest.Update req
	){
		komoditasService.update(req);
		return CommonResponse.updated(Komoditas.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(
			@RequestBody @Valid SetDeleteStatusRequest req
	){
		komoditasService.softDelete(req);
		return CommonResponse.toggledSoftDelete(Komoditas.class, req);
	}
}