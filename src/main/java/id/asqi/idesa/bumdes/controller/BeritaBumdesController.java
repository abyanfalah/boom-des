package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.http.request.BeritaBumdesRequest;
import id.asqi.idesa.bumdes.model.BeritaBumdes;
import id.asqi.idesa.bumdes.service.BeritaBumdesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("berita-bumdes")
@RequiredArgsConstructor
public class BeritaBumdesController {
	private final BeritaBumdesService beritaBumdesService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<BeritaBumdes>>> getAll(
			@RequestBody @Valid SearchPaginationRequest req
	){
		Page<BeritaBumdes> page = beritaBumdesService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(
		    @RequestBody @Valid BeritaBumdesRequest.Create req
	) throws Exception {
		beritaBumdesService.create(req);
		return CommonResponse.created(BeritaBumdes.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(
//			@Valid BeritaBumdesRequest.Update req
			@RequestBody @Valid BeritaBumdesRequest.Update req
	){
		beritaBumdesService.update(req);
		return CommonResponse.updated(BeritaBumdes.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(
			@RequestBody @Valid IdNumberRequest req
	){
		beritaBumdesService.delete(req);
		return CommonResponse.deleted(BeritaBumdes.class);
	}
}