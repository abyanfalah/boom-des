package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.core.http.request.TagihanPendudukRequest;
import id.asqi.idesa.bumdes.model.TagihanPenduduk;
import id.asqi.idesa.bumdes.service.TagihanPendudukService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tagihan-penduduk")
@RequiredArgsConstructor
public class TagihanPendudukController {

	private final TagihanPendudukService tagihanPendudukService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<TagihanPenduduk>>> getAll(@RequestBody @Valid TagihanPendudukRequest.Filter req) {
		Page<TagihanPenduduk> page = tagihanPendudukService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(@RequestBody @Valid TagihanPendudukRequest.Create req) {
		tagihanPendudukService.create(req);
		return CommonResponse.created(TagihanPenduduk.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(@RequestBody @Valid TagihanPendudukRequest.Update req) {
		tagihanPendudukService.update(req);
		return CommonResponse.updated(TagihanPenduduk.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(@RequestBody @Valid SetDeleteStatusRequest req) {
		tagihanPendudukService.delete(req);
		return CommonResponse.toggledSoftDelete(TagihanPenduduk.class, req);
	}
}