package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.http.request.ProdukGrosirRequest;
import id.asqi.idesa.bumdes.model.ProdukGrosir;
import id.asqi.idesa.bumdes.service.ProdukGrosirService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produk-grosir")
@RequiredArgsConstructor
public class ProdukGrosirController {
	private final ProdukGrosirService produkGrosirService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<ProdukGrosir>>> getAll(
			@RequestBody @Valid SearchPaginationRequest req
	){
		Page<ProdukGrosir> page = produkGrosirService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(
			@Valid ProdukGrosirRequest.Create req
	) throws Exception {
		produkGrosirService.create(req);
		return CommonResponse.created(ProdukGrosir.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(
//			@Valid ProdukGrosirRequest.Update req
			@RequestBody @Valid ProdukGrosirRequest.Update req
	) throws Exception {
		produkGrosirService.update(req);
		return CommonResponse.updated(ProdukGrosir.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(
			@RequestBody @Valid IdNumberRequest req
	){
		produkGrosirService.delete(req);
		return CommonResponse.deleted(ProdukGrosir.class);
	}
}