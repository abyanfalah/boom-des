package id.asqi.idesa.bumdes.core.controller;

import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.core.service.MappingTagihanPendudukService;
import id.asqi.idesa.bumdes.http.request.MappingTagihanPendudukRequest;
import id.asqi.idesa.bumdes.model.MappingTagihanPenduduk;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mapping-tagihan-penduduk")
@RequiredArgsConstructor
public class MappingTagihanPendudukController {

	private final MappingTagihanPendudukService mappingTagihanPendudukService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<MappingTagihanPenduduk>>> getAll(
			@RequestBody @Valid MappingTagihanPendudukRequest.Filter req, Pageable pageable) {
		Page<MappingTagihanPenduduk> page = mappingTagihanPendudukService.getAll(req, pageable);
		return CommonResponse.paginated(page);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(
			@RequestBody @Valid MappingTagihanPendudukRequest.Create req) {
		mappingTagihanPendudukService.create(req);
		return CommonResponse.created(MappingTagihanPenduduk.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(
			@RequestBody @Valid MappingTagihanPendudukRequest.Update req) {
		mappingTagihanPendudukService.update(req);
		return CommonResponse.updated(MappingTagihanPenduduk.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(
			@RequestBody @Valid SetDeleteStatusRequest req) {
		mappingTagihanPendudukService.delete(req);
		return CommonResponse.toggledSoftDelete(MappingTagihanPenduduk.class, req);
	}
}