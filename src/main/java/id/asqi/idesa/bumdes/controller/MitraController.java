package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.component.exception.InvalidOperationException;
import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.http.request.MitraRequest;
import id.asqi.idesa.bumdes.model.Mitra;
import id.asqi.idesa.bumdes.service.MitraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mitra")
@RequiredArgsConstructor
public class MitraController {
	private final MitraService mitraService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<Mitra>>> getAll(
			@RequestBody @Valid SearchPaginationRequest req
	){
		Page<Mitra> page = mitraService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(
		    @RequestBody @Valid MitraRequest.Create req
	) throws Exception {
		mitraService.create(req);
		return CommonResponse.created(Mitra.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(
			@RequestBody @Valid MitraRequest.Update req
	){
		mitraService.update(req);
		return CommonResponse.updated(Mitra.class);
	}

	@PostMapping("set-status")
	public ResponseEntity<Response<Void>> setStatus(
			@RequestBody @Valid MitraRequest.SetStatus req
	){
		mitraService.setStatus(req);
		return CommonResponse.updated("Status mitra berhasil di perbarui");
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(
			@RequestBody @Valid IdNumberRequest req
	){
		throw new InvalidOperationException("Tidak bisa menghapus mitra");
	}
}