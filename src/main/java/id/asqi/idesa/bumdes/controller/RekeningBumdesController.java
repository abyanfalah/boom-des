package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.http.request.RekeningUserBumdesRequest;
import id.asqi.idesa.bumdes.model.RekeningUserBumdes;
import id.asqi.idesa.bumdes.service.RekeningUserBumdesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rekening-user-bumdes")
@RequiredArgsConstructor
public class RekeningBumdesController {
	private final RekeningUserBumdesService rekeningUserBumdesService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<RekeningUserBumdes>>> getAll(
			@RequestBody SearchPaginationRequest req
	){
		Page<RekeningUserBumdes> page = rekeningUserBumdesService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping
	public ResponseEntity<Response<Void>> create(
			@RequestBody RekeningUserBumdesRequest.Create req
	){
		rekeningUserBumdesService.create(req);
		return CommonResponse.created(RekeningUserBumdes.class);
	}

	@PutMapping
	public ResponseEntity<Response<Void>> update(
			@RequestBody RekeningUserBumdesRequest.Update req
	){
		rekeningUserBumdesService.update(req);
		return CommonResponse.updated(RekeningUserBumdes.class);
	}
}