package id.asqi.idesa.bumdes.service;


import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.http.request.SetStatusRequest;
import id.asqi.idesa.bumdes.http.request.ProdukMarketplaceDesaRequest;
import id.asqi.idesa.bumdes.model.*;
import id.asqi.idesa.bumdes.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdukMarketplaceDesaService {
	private final ProdukMarketplaceDesaRepository produkMarketplaceDesaRepository;
	private final JenisVariasiProdukMarketplaceDesaRepository jenisVariasiRepository;
	private final VarianProdukMarketplaceDesaRepository varianRepository;
	private final MappingVarianProdukMarketplaceDesaRepository mappingVarianRepository;
	private final SatuanRepository satuanRepository;
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

	@Transactional
	public void create (ProdukMarketplaceDesaRequest.Create req) throws Exception {
		KategoriProdukMarketplaceDesa kategori = this.getKategori(req.getKategoriProdukMarketplaceDesaId());
		Satuan satuan = this.getSatuan(req.getSatuanId());

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


		e.setTanggalDibuat(LocalDateTime.now());
		e = produkMarketplaceDesaRepository.save(e);

		this.handleHargaJualGrosir(e,req);
		this.handleVarian(e, req);
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

	private KategoriProdukMarketplaceDesa getKategori (Long id){
		return kategoriRepository.findById(id).orElseThrow(() -> new NotFoundEntity(KategoriProdukMarketplaceDesa.class));
	}

	private Satuan getSatuan (Long id){
		return satuanRepository.findById(id).orElseThrow(() -> new NotFoundEntity(Satuan.class));
	}

	private void handleHargaJualGrosir (ProdukMarketplaceDesa e, ProdukMarketplaceDesaRequest.Create req) {
		if(req.getHargaJualGrosir() == null || req.getHargaJualGrosir().isEmpty()) return;

		List<HargaGrosir> result = new ArrayList<>();
		for (ProdukMarketplaceDesaRequest.HargaJualGrosir item : req.getHargaJualGrosir()) {
			HargaGrosir hargaGrosir = new HargaGrosir();
			hargaGrosir.setId(Constants.idGenerator());
			hargaGrosir.setProdukMarketplaceDesa(e);
			hargaGrosir.setKuantitasMinimum(item.getMinimalPembelian());
			hargaGrosir.setHarga(item.getHargaJualGrosir());
			result.add(hargaGrosir);
		}
		hargaGrosirRepository.saveAll(result);
	}

	private void handleVarian (ProdukMarketplaceDesa e, ProdukMarketplaceDesaRequest.Create req) {
		if(req.getJenisVarian() == null || req.getJenisVarian().isEmpty()) return;

		List<JenisVariasiProdukMarketplaceDesa> jenisVariasiList = new ArrayList<>();
		List<OpsiVariasiProdukMarketplaceDesa> opsiList = new ArrayList<>();

		for(ProdukMarketplaceDesaRequest.JenisVarian jenisVarian : req.getJenisVarian()) {
			JenisVariasiProdukMarketplaceDesa jv = new JenisVariasiProdukMarketplaceDesa();
			jv.setId(Constants.idGenerator());
			jv.setProdukMarketplaceDesa(e);
			jv.setNama(jenisVarian.getNama());
			jv = jenisVariasiRepository.save(jv);

			for(ProdukMarketplaceDesaRequest.OpsiVarian opsiVarian : jenisVarian.getOpsiVarian()) {
				OpsiVariasiProdukMarketplaceDesa ov = new OpsiVariasiProdukMarketplaceDesa();
				ov.setId(Constants.idGenerator());
				ov.setJenisVariasiProdukMarketplaceDesa(jv);
				ov.setNama(opsiVarian.getNama());
				opsiList.add(ov);
			}
			opsiVariasiRepository.saveAll(opsiList);
		}

	}


}