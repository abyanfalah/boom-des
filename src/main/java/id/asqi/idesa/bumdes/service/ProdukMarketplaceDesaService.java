package id.asqi.idesa.bumdes.service;


import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.http.request.SetStatusRequest;
import id.asqi.idesa.bumdes.dto.ProdukMarketplaceDesaDTO;
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
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdukMarketplaceDesaService {
	private final ProdukMarketplaceDesaRepository produkMarketplaceDesaRepository;
	private final JenisVariasiProdukMarketplaceDesaRepository jenisVariasiRepository;
	private final VarianProdukMarketplaceDesaRepository varianRepository;
	//	private final MappingVarianProdukMarketplaceDesaRepository mappingVarianRepository;
	private final SatuanRepository satuanRepository;
	private final OpsiVariasiProdukMarketplaceDesaRepository opsiVariasiRepository;
	private final KategoriProdukMarketplaceDesaRepository kategoriRepository;
	private final HargaGrosirRepository hargaGrosirRepository;

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

		List<HargaGrosir> hargaGrosir = this.handleHargaJualGrosir(e, req);
		e.setHargaGrosir(hargaGrosir);

		List<JenisVariasiProdukMarketplaceDesa> jenisVariasi = this.handleJenisVariasiWithOpsi(e, req);
		e.setJenisVariasi(jenisVariasi);

		List<VarianProdukMarketplaceDesa> varian = this.handleVarian(e, req, jenisVariasi);
		e.setVarian(varian);

		e.setTanggalDibuat(LocalDateTime.now());
		produkMarketplaceDesaRepository.save(e);
	}

//	public void update (ProdukMarketplaceDesaRequest.Update req) {
//		ProdukMarketplaceDesa e = this.findById(req.getId());
//
//		KategoriProdukMarketplaceDesa kategori = this.getKategori(req.getKategoriProdukMarketplaceDesaId());
//		Satuan satuan = this.getSatuan(req.getSatuanId());
//		e.setNama(req.getNama());
//		e.setKategoriProdukMarketplaceDesa(kategori);
//		e.setKondisi(req.getKondisi());
//		e.setDeskripsi(req.getDeskripsi());
//		e.setIsAktif(req.getIsAktif());
//		e.setSatuan(satuan);
//		e.setBobotSatuan(req.getBobotSatuan());
//		e.setUserBumdes(Auth.getUserBumdes());
//		e.setAlamatDesa(Auth.getAlamatDesa());
//		e.setStok(req.getStok());
//		e.setSku(req.getSku());
//		e.setHargaJualUtama(req.getHargaJualUtama());
//		e.setHargaModal(req.getHargaModal());
//		e.setFeeAplikasi(req.getFeeAplikasi());
//		e.setHasVarian(req.getHasVarian());
//
//		List<HargaGrosir> hargaGrosir = this.handleHargaJualGrosir(e, req);
//		e.setHargaGrosir(hargaGrosir);
//
//		List<JenisVariasiProdukMarketplaceDesa> jenisVariasi = this.handleJenisVariasiWithOpsi(e, req);
//		e.setJenisVariasi(jenisVariasi);
//
//		List<VarianProdukMarketplaceDesa> varian = this.handleVarian(e, req, jenisVariasi);
//		e.setVarian(varian);
//
//		e.setTanggalDiubah(LocalDateTime.now());
//		produkMarketplaceDesaRepository.save(e);
//	}

	public void setStatus (SetStatusRequest req) {
		ProdukMarketplaceDesa e = this.findById(req.getId());

		e.setIsAktif(req.getIsAktif());
		e.setTanggalDiubah(LocalDateTime.now());

		produkMarketplaceDesaRepository.save(e);
	}

	private ProdukMarketplaceDesa findById(Long id){
		return produkMarketplaceDesaRepository.findById(id).orElseThrow(() -> new NotFoundEntity(ProdukMarketplaceDesa.class));
	}

	private KategoriProdukMarketplaceDesa getKategori (Long id) {
		return kategoriRepository.findById(id).orElseThrow(() -> new NotFoundEntity(KategoriProdukMarketplaceDesa.class));
	}

	private Satuan getSatuan (Long id) {
		return satuanRepository.findById(id).orElseThrow(() -> new NotFoundEntity(Satuan.class));
	}

	private List<HargaGrosir> handleHargaJualGrosir (ProdukMarketplaceDesa e, ProdukMarketplaceDesaRequest.Create req) {
		if (req.getHargaJualGrosir() == null || req.getHargaJualGrosir().isEmpty()) return null;

		List<HargaGrosir> result = new ArrayList<>();
		for (ProdukMarketplaceDesaRequest.Create.HargaJualGrosir item : req.getHargaJualGrosir()) {
			HargaGrosir hargaGrosir = new HargaGrosir();
			hargaGrosir.setId(Constants.idGenerator());
			hargaGrosir.setProdukMarketplaceDesa(e);
			hargaGrosir.setKuantitasMinimum(item.getMinimalPembelian());
			hargaGrosir.setHarga(item.getHargaJualGrosir());
			result.add(hargaGrosir);
		}
		return result;
	}

	/*saves variants and variants options*/
	private List<JenisVariasiProdukMarketplaceDesa> handleJenisVariasiWithOpsi (ProdukMarketplaceDesa e, ProdukMarketplaceDesaRequest.Create req) {
		if (req.getJenisVarian() == null || req.getJenisVarian().isEmpty()) return null;

		List<JenisVariasiProdukMarketplaceDesa> jvList = new ArrayList<>();
		List<OpsiVariasiProdukMarketplaceDesa> ovList = new ArrayList<>();

		for (ProdukMarketplaceDesaRequest.Create.JenisVarian jenisVarian : req.getJenisVarian()) {
			JenisVariasiProdukMarketplaceDesa jv = new JenisVariasiProdukMarketplaceDesa();
			jv.setId(Constants.idGenerator());
			jv.setProdukMarketplaceDesa(e);
			jv.setNama(jenisVarian.getNama());

			for (ProdukMarketplaceDesaRequest.Create.OpsiVarian opsiVarian : jenisVarian.getOpsiVarian()) {
				OpsiVariasiProdukMarketplaceDesa ov = new OpsiVariasiProdukMarketplaceDesa();
				ov.setId(Constants.idGenerator());
				ov.setJenisVariasiProdukMarketplaceDesa(jv);
				ov.setNama(opsiVarian.getNama());
				ovList.add(ov);
			}

			jv.setOpsiVariasi(ovList);
			jvList.add(jv);
		}

		return jvList;
	}

	private List<VarianProdukMarketplaceDesa> handleVarian (ProdukMarketplaceDesa e, ProdukMarketplaceDesaRequest.Create req, List<JenisVariasiProdukMarketplaceDesa> jvList) {
		if (req.getVarian() == null || req.getVarian().isEmpty()) return null;

		List<OpsiVariasiProdukMarketplaceDesa> ovList =jvList.stream()
				.flatMap(jv -> jv.getOpsiVariasi().stream())
				.toList();

		List<VarianProdukMarketplaceDesa> varianList = new ArrayList<>();
		for (ProdukMarketplaceDesaRequest.Create.Varian varian : req.getVarian()) {
			VarianProdukMarketplaceDesa v = new VarianProdukMarketplaceDesa();
			v.setId(Constants.idGenerator());
			v.setProdukMarketplaceDesa(e);
			v.setHargaJualUtama(varian.getHargaJualUtama());
			v.setHargaModal(varian.getHargaModal());
			v.setSatuan(this.getSatuan(varian.getSatuanId()));
			v.setBobotSatuan(varian.getBobotSatuan());
			v.setSku(varian.getSku());
			v.setStok(varian.getStok());
			v.setIsAktif(varian.getIsAktif());
			v.setOpsiVariasi(this.getCreateChoosenOpsi(varian, ovList));
			v.setIsUtama(varian.getIsUtama());
			v.setTanggalDibuat(LocalDateTime.now());
			varianList.add(v);
		}
		return varianList;
	}

	private List<OpsiVariasiProdukMarketplaceDesa> getCreateChoosenOpsi(
			ProdukMarketplaceDesaRequest.Create.Varian varian,
			List<OpsiVariasiProdukMarketplaceDesa> ovList) {
		return varian.getOpsiVarian().stream()
				.flatMap(jenisOpsi -> ovList.stream()
						.filter(ov ->
								ov.getJenisVariasiProdukMarketplaceDesa().getNama().equals(jenisOpsi.getNamaJenisVariasi())
										&& ov.getNama().equals(jenisOpsi.getNamaOpsiVariasi()))
				)
				.toList();
	}
//
//	private void handleUpdateHargaJualGrosir(ProdukMarketplaceDesa e, ProdukMarketplaceDesaRequest.Update req) {
//		List<HargaGrosir> existingHargaGrosir = e.getHargaGrosir();
//		List<HargaGrosir> newHargaGrosir = new ArrayList<>();
//		List<HargaGrosir> updatedHargaGrosir = new ArrayList<>();
//		List<HargaGrosir> toRemoveHargaGrosir = new ArrayList<>(existingHargaGrosir);
//
//		for (ProdukMarketplaceDesaRequest.Update.HargaJualGrosir item : req.getHargaJualGrosir()) {
//			/*create new ones*/
//			if (item.isNew()) {
//				HargaGrosir hargaGrosir = new HargaGrosir();
//				hargaGrosir.setId(Constants.idGenerator());
//				hargaGrosir.setProdukMarketplaceDesa(e);
//				hargaGrosir.setKuantitasMinimum(item.getMinimalPembelian());
//				hargaGrosir.setHarga(item.getHargaJualGrosir());
//				newHargaGrosir.add(hargaGrosir);
//				continue;
//			}
//
//			/*update existing ones*/
//			existingHargaGrosir.stream()
//					.filter(hg -> Objects.equals(hg.getId(), item.getId()))
//					.findFirst()
//					.ifPresent(existingHg -> {
//						existingHg.setKuantitasMinimum(item.getMinimalPembelian());
//						existingHg.setHarga(item.getHargaJualGrosir());
//						updatedHargaGrosir.add(existingHg);
//						toRemoveHargaGrosir.removeIf(hg -> Objects.equals(hg.getId(), item.getId())); //removes the updated item from the remove list.
//					});
//		}
//
//		List<HargaGrosir> allHargaGrosir = new ArrayList<>();
//		allHargaGrosir.addAll(newHargaGrosir);
//		allHargaGrosir.addAll(updatedHargaGrosir);
//
//		e.setHargaGrosir(new ArrayList<>()); //clear the old list.
//		e.setHargaGrosir(allHargaGrosir);
//
//		produkMarketplaceDesaRepository.save(e);
//	}
//
//
//
//	private void handleUpdateVarian (ProdukMarketplaceDesa e, ProdukMarketplaceDesaRequest.Update req, List<OpsiVariasiProdukMarketplaceDesa> ovList) {
//		if (req.getVarian() == null || req.getVarian().isEmpty()) return;
//
//		List<VarianProdukMarketplaceDesa> result = new ArrayList<>();
//		for (ProdukMarketplaceDesaRequest.Update.Varian varian : req.getVarian()) {
//			VarianProdukMarketplaceDesa v = new VarianProdukMarketplaceDesa();
//			v.setId(Constants.idGenerator());
//			v.setProdukMarketplaceDesa(e);
//			v.setHargaJualUtama(varian.getHargaJualUtama());
//			v.setHargaModal(varian.getHargaModal());
//			v.setSatuan(this.getSatuan(varian.getSatuanId()));
//			v.setBobotSatuan(varian.getBobotSatuan());
//			v.setSku(varian.getSku());
//			v.setStok(varian.getStok());
//			v.setIsAktif(varian.getIsAktif());
//			v.setOpsiVariasi(this.getChoosenOpsi(varian, ovList));
//			v.setIsUtama(varian.getIsUtama());
//			v.setTanggalDibuat(LocalDateTime.now());
//			result.add(v);
//		}
//		varianRepository.saveAll(result);
//	}
//
//
//	private HargaGrosir findHargaGrosir (Long hargaGrosirId){
//		return hargaGrosirRepository.findById(hargaGrosirId).orElseThrow(() -> new NotFoundEntity(HargaGrosir.class));
//	}
}