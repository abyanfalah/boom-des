package id.asqi.idesa.bumdes.controller;

import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.PendudukRequest;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.core.service.PendudukService;
import id.asqi.idesa.bumdes.model.Penduduk;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("penduduk")
@RequiredArgsConstructor
public class PendudukController {
	private final PendudukService pendudukService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<Penduduk>>> getAll(@RequestBody @Valid PendudukRequest.Filter req) {
		Page<Penduduk> page = pendudukService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(@RequestBody @Valid PendudukRequest.Create req) {
		pendudukService.create(req);
		return CommonResponse.created(Penduduk.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(@RequestBody @Valid PendudukRequest.Update req) {
		pendudukService.update(req);
		return CommonResponse.updated(Penduduk.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(@RequestBody @Valid SetDeleteStatusRequest req) {
		pendudukService.delete(req);
		return CommonResponse.toggledSoftDelete(Penduduk.class, req);
	}
}