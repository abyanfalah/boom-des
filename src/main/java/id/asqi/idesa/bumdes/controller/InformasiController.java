package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.http.request.InformasiRequest;
import id.asqi.idesa.bumdes.model.Informasi;
import id.asqi.idesa.bumdes.service.InformasiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("informasi")
@RequiredArgsConstructor
public class InformasiController {
	private final InformasiService informasiService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<Informasi>>> getAll(
			@RequestBody @Valid SearchPaginationRequest req
	){
		Page<Informasi> page = informasiService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(
			@Valid InformasiRequest.Create req
	) throws Exception {
		informasiService.create(req);
		return CommonResponse.created(Informasi.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(
//			@Valid InformasiRequest.Update req
			@RequestBody @Valid InformasiRequest.Update req
	){
		informasiService.update(req);
		return CommonResponse.updated(Informasi.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(
			@RequestBody @Valid IdNumberRequest req
	){
		informasiService.delete(req);
		return CommonResponse.deleted(Informasi.class);
	}
}