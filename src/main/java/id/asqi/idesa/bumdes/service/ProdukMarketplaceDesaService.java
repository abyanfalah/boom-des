package id.asqi.idesa.bumdes.service;


import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.http.request.SetStatusRequest;
import id.asqi.idesa.bumdes.http.request.ProdukMarketplaceDesaRequest;
import id.asqi.idesa.bumdes.model.ProdukMarketplaceDesa;
import id.asqi.idesa.bumdes.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProdukMarketplaceDesaService {
	private final ProdukMarketplaceDesaRepository produkMarketplaceDesaRepository;
	private final JenisVariasiProdukMarketplaceDesaRepository jenisVariasiRepository;
	private final VarianProdukMarketplaceDesaRepository varianRepository;
	private final MappingVarianProdukMarketplaceDesaRepository mappingVarianRepository;
	private final OpsiVariasiProdukMarketplaceDesaRepository opsiVariasiRepository;
	private final KategoriProdukMarketplaceDesaRepository kategoriRepository;
	private final HargaGrosirRepository hargaGrosirRepository;

	public Page<ProdukMarketplaceDesa> getAll (SearchPaginationRequest req) {
		return produkMarketplaceDesaRepository.search(
				req.getSearch(),
				Auth.getAlamatDesaId(),
				req.getPagination()
		);
	}

	public void create (ProdukMarketplaceDesaRequest.Create req) throws Exception {
		ProdukMarketplaceDesa e = new ProdukMarketplaceDesa();
		e.setId(Constants.idGenerator());
		e.setTanggalDibuat(LocalDateTime.now());
		produkMarketplaceDesaRepository.save(e);
	}

	public void update (ProdukMarketplaceDesaRequest.Update req) {
		ProdukMarketplaceDesa e = produkMarketplaceDesaRepository.findById(req.getId()).orElseThrow(() -> new NotFoundEntity(ProdukMarketplaceDesa.class));
		e.setTanggalDiubah(LocalDateTime.now());
		produkMarketplaceDesaRepository.save(e);
	}

	public void setStatus (SetStatusRequest req) {
		ProdukMarketplaceDesa e = produkMarketplaceDesaRepository.findById(req.getId()).orElseThrow(() -> new NotFoundEntity(ProdukMarketplaceDesa.class));

		e.setIsAktif(req.getIsAktif());
		e.setTanggalDiubah(LocalDateTime.now());

		produkMarketplaceDesaRepository.save(e);
	}
}