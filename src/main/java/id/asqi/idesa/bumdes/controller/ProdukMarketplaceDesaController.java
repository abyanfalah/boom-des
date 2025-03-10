package id.asqi.idesa.bumdes.controller;


import id.asqi.idesa.bumdes.core.component.exception.InvalidOperationException;
import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.dto.ProdukMarketplaceDesaDTO;
import id.asqi.idesa.bumdes.http.request.ProdukMarketplaceDesaRequest;
import id.asqi.idesa.bumdes.model.ProdukMarketplaceDesa;
import id.asqi.idesa.bumdes.service.ProdukMarketplaceDesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produk-marketplace-desa")
@RequiredArgsConstructor
public class ProdukMarketplaceDesaController {
	private final ProdukMarketplaceDesaService produkMarketplaceDesaService;

	@PostMapping("all")
	public ResponseEntity<Response<Page<ProdukMarketplaceDesaDTO>>> getAll(
			@RequestBody @Valid SearchPaginationRequest req
	){
		Page<ProdukMarketplaceDesaDTO> page = produkMarketplaceDesaService.getAll(req);
		return CommonResponse.paginated(page);
	}

	@PostMapping("create")
	public ResponseEntity<Response<Void>> create(
			@ModelAttribute @Valid ProdukMarketplaceDesaRequest.Create req
	) throws Exception {
		produkMarketplaceDesaService.create(req);
		return CommonResponse.created(ProdukMarketplaceDesa.class);
	}

	@PostMapping("update")
	public ResponseEntity<Response<Void>> update(
			@RequestBody @Valid ProdukMarketplaceDesaRequest.Update req
	){
		produkMarketplaceDesaService.update(req);
		return CommonResponse.updated(ProdukMarketplaceDesa.class);
	}

	@PostMapping("delete")
	public ResponseEntity<Response<Void>> delete(
			@RequestBody @Valid IdNumberRequest req
	){
		/*soft delete?*/
		throw new InvalidOperationException("Tidak bisa menghapus ProdukMarketplaceDesa");
	}
}