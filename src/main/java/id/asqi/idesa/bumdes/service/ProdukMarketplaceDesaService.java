package id.asqi.idesa.bumdes.service;


import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.core.http.request.SetStatusRequest;
import id.asqi.idesa.bumdes.core.service.Validator;
import id.asqi.idesa.bumdes.dto.ProdukMarketplaceDesaDTO;
import id.asqi.idesa.bumdes.http.request.ProdukMarketplaceDesaRequest;
import id.asqi.idesa.bumdes.model.*;
import id.asqi.idesa.bumdes.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdukMarketplaceDesaService {
	private final ProdukMarketplaceDesaRepository produkMarketplaceDesaRepository;
	private final ProdukMarketplaceDesaServiceHelper serviceHelper;

	/*uses DTO*/
	public Page<ProdukMarketplaceDesaDTO> getAll (SearchPaginationRequest req) {
		return produkMarketplaceDesaRepository.searchDTO(
				req.getSearch(),
				Auth.getAlamatDesaId(),
				req.getPagination()
		);
	}

	@Transactional
	public void create (ProdukMarketplaceDesaRequest.Create req) throws Exception {
		KategoriProdukMarketplaceDesa kategori = serviceHelper.getKategori(req.getKategoriProdukMarketplaceDesaId());
		Satuan satuan = serviceHelper.getSatuan(req.getSatuanId());

		ProdukMarketplaceDesa e = new ProdukMarketplaceDesa();
		e.setId(Constants.idGenerator());
		e.setNama(req.getNama());
		e.setKategoriProdukMarketplaceDesa(kategori);
		e.setKondisi(req.getKondisi());
		e.setDeskripsi(req.getDeskripsi());
		e.setIsAktif(req.getIsAktif());
		e.setSatuan(satuan);
		e.setBobotSatuan(req.getBobotSatuan());
		e.setUserBumdes(Auth.getUserBumdes());
		e.setAlamatDesa(Auth.getAlamatDesa());
		e.setStok(req.getStok());
		e.setSku(req.getSku());
		e.setHargaJualUtama(req.getHargaJualUtama());
		e.setHargaModal(req.getHargaModal());
		e.setFeeAplikasi(req.getFeeAplikasi());
		e.setHasVarian(req.getHasVarian());

		List<HargaGrosir> hargaGrosir = serviceHelper.handleHargaJualGrosir(e, req);
		e.setHargaGrosir(hargaGrosir);

		List<JenisVariasiProdukMarketplaceDesa> jenisVariasi = serviceHelper.handleJenisVariasiWithOpsi(e, req);
		e.setJenisVariasi(jenisVariasi);

		List<VarianProdukMarketplaceDesa> varian = serviceHelper.handleVarian(e, req, jenisVariasi);
		e.setVarian(varian);

		e.setTanggalDibuat(LocalDateTime.now());
		produkMarketplaceDesaRepository.save(e);
	}

	public void update (ProdukMarketplaceDesaRequest.Update req) {
		ProdukMarketplaceDesa e = this.findById(req.getId());
		Validator.deletionCheck(e.getTanggalDihapus(), ProdukMarketplaceDesa.class);

		KategoriProdukMarketplaceDesa kategori = serviceHelper.getKategori(req.getKategoriProdukMarketplaceDesaId());
		Satuan satuan = serviceHelper.getSatuan(req.getSatuanId());
		e.setNama(req.getNama());
		e.setKategoriProdukMarketplaceDesa(kategori);
		e.setKondisi(req.getKondisi());
		e.setDeskripsi(req.getDeskripsi());
		e.setIsAktif(req.getIsAktif());
		e.setSatuan(satuan);
		e.setBobotSatuan(req.getBobotSatuan());
		e.setUserBumdes(Auth.getUserBumdes());
		e.setAlamatDesa(Auth.getAlamatDesa());
		e.setStok(req.getStok());
		e.setSku(req.getSku());
		e.setHargaJualUtama(req.getHargaJualUtama());
		e.setHargaModal(req.getHargaModal());
		e.setFeeAplikasi(req.getFeeAplikasi());
		e.setHasVarian(req.getHasVarian());

		List<HargaGrosir> hargaGrosir = serviceHelper.handleUpdateHargaJualGrosir(e, req);
		e.setHargaGrosir(hargaGrosir);

		List<JenisVariasiProdukMarketplaceDesa> jenisVariasi = serviceHelper.handleUpdateJenisVariasiWithOpsi(e, req);
		e.setJenisVariasi(jenisVariasi);

		List<VarianProdukMarketplaceDesa> varian = serviceHelper.handleUpdateVarian(e, req, jenisVariasi);
		e.setVarian(varian);

		e.setTanggalDiubah(LocalDateTime.now());
		produkMarketplaceDesaRepository.save(e);
	}

	public void setStatus (SetStatusRequest req) {
		ProdukMarketplaceDesa e = this.findById(req.getId());

		e.setIsAktif(req.getIsAktif());
		e.setTanggalDiubah(LocalDateTime.now());

		produkMarketplaceDesaRepository.save(e);
	}

	public void softDelete(SetDeleteStatusRequest req){
		ProdukMarketplaceDesa e = this.findById(req.getId());

		e.setTanggalDihapus(
				req.getIsDeleted() ?
						LocalDateTime.now()
						: null
		);

		produkMarketplaceDesaRepository.save(e);
	}

	private ProdukMarketplaceDesa findById(Long id){
		return produkMarketplaceDesaRepository.findById(id).orElseThrow(() -> new NotFoundEntity(ProdukMarketplaceDesa.class));
	}
}