package id.asqi.idesa.bumdes.controller;

import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.http.request.KomentarPostinganForumBumdesRequest;
import id.asqi.idesa.bumdes.model.KomentarPostinganForumBumdes;
import id.asqi.idesa.bumdes.service.KomentarPostinganForumBumdesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("komentar-postingan-forum-bumdes")
@RequiredArgsConstructor
public class KomentarPostinganForumBumdesController {

	private final KomentarPostinganForumBumdesService komentarPostinganForumBumdesService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<KomentarPostinganForumBumdes>>> getAll(
			@RequestBody @Valid KomentarPostinganForumBumdesRequest.Filter req
	) {
		Page<KomentarPostinganForumBumdes> page = komentarPostinganForumBumdesService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(
			@RequestBody @Valid KomentarPostinganForumBumdesRequest.Create req
	) {
		komentarPostinganForumBumdesService.create(req);
		return CommonResponse.created(KomentarPostinganForumBumdes.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(
			@RequestBody @Valid KomentarPostinganForumBumdesRequest.Update req
	) {
		komentarPostinganForumBumdesService.update(req);
		return CommonResponse.updated(KomentarPostinganForumBumdes.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(
			@RequestBody @Valid SetDeleteStatusRequest req
	) {
		komentarPostinganForumBumdesService.delete(req);
		return CommonResponse.deleted(KomentarPostinganForumBumdes.class);
	}
}