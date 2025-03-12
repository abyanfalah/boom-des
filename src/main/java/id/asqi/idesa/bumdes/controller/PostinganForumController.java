package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.http.request.PostinganForumRequest;
import id.asqi.idesa.bumdes.model.PostinganForum;
import id.asqi.idesa.bumdes.service.PostinganForumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("postingan-forum-bumdes")
@RequiredArgsConstructor
public class PostinganForumController {

	private final PostinganForumService postinganForumService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<PostinganForum>>> getAll(@RequestBody @Valid PostinganForumRequest.Filter req) {
		return CommonResponse.paginated(postinganForumService.getAll(req));
	}
	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(@Valid PostinganForumRequest.Create req) throws Exception {
		postinganForumService.create(req);
		return CommonResponse.created(PostinganForum.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(@Valid PostinganForumRequest.Update req) {
		postinganForumService.update(req);
		return CommonResponse.updated(PostinganForum.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(@RequestBody @Valid SetDeleteStatusRequest req) {
		postinganForumService.softDelete(req);
		return CommonResponse.toggledSoftDelete(PostinganForum.class, req);
	}
}