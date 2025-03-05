package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.http.request.MetodePengirimanRequest;
import id.asqi.idesa.bumdes.model.MetodePengiriman;
import id.asqi.idesa.bumdes.service.MetodePengirimanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("metode-pengiriman")
@RequiredArgsConstructor
public class MetodePengirimanController {
	private final MetodePengirimanService metodePengirimanService;

	@PostMapping("all")
	public ResponseEntity<Response<List<MetodePengiriman>>> getAll(){
		List<MetodePengiriman> list = metodePengirimanService.getAll();
		return CommonResponse.data(list);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(
			@RequestBody @Valid MetodePengirimanRequest.Create req
	) throws Exception {
		metodePengirimanService.create(req);
		return CommonResponse.created(MetodePengiriman.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(
			@RequestBody @Valid MetodePengirimanRequest.Update req
	){
		metodePengirimanService.update(req);
		return CommonResponse.updated(MetodePengiriman.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(
			@RequestBody @Valid IdNumberRequest req
	){
		metodePengirimanService.delete(req);
		return CommonResponse.deleted(MetodePengiriman.class);
	}
}