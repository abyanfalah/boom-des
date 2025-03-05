package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.http.request.InformasiTokoDesaRequest;
import id.asqi.idesa.bumdes.model.InformasiTokoDesa;
import id.asqi.idesa.bumdes.service.InformasiTokoDesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("informasi-toko-desa")
@RequiredArgsConstructor
public class InformasiTokoDesaController {
	private final InformasiTokoDesaService informasiTokoDesaService;

	@PostMapping("get")
	public ResponseEntity<Response<InformasiTokoDesa>> getAll () {
		InformasiTokoDesa informasiTokoDesa = informasiTokoDesaService.get();
		return CommonResponse.data(informasiTokoDesa);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create (
			@Valid InformasiTokoDesaRequest.Create req
	) throws Exception {
		informasiTokoDesaService.create(req);
		return CommonResponse.created(InformasiTokoDesa.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update (
			@Valid InformasiTokoDesaRequest.Update req
	) throws Exception {
		informasiTokoDesaService.update(req);
		return CommonResponse.updated(InformasiTokoDesa.class);
	}
}