package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.http.request.RekeningUserBumdesRequest;
import id.asqi.idesa.bumdes.model.RekeningUserBumdes;
import id.asqi.idesa.bumdes.service.RekeningUserBumdesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rekening-user-bumdes")
@RequiredArgsConstructor
public class RekeningUserBumdesController {
	private final RekeningUserBumdesService rekeningUserBumdesService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<RekeningUserBumdes>>> getAll(
			@RequestBody @Valid SearchPaginationRequest req
	){
		Page<RekeningUserBumdes> page = rekeningUserBumdesService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping
	public ResponseEntity<Response<Void>> create(
			@RequestBody @Valid RekeningUserBumdesRequest.Create req
	){
		rekeningUserBumdesService.create(req);
		return CommonResponse.created(RekeningUserBumdes.class);
	}

	@PostMapping
	public ResponseEntity<Response<Void>> setRekeningUtama(
			@RequestBody @Valid IdNumberRequest req
	){
		rekeningUserBumdesService.setRekeningUtama(req);
		return CommonResponse.success("Berhasil set rekening utama");
	}

	@PutMapping
	public ResponseEntity<Response<Void>> update(
			@RequestBody @Valid RekeningUserBumdesRequest.Update req
	){
		rekeningUserBumdesService.update(req);
		return CommonResponse.updated(RekeningUserBumdes.class);
	}
}