package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.http.request.ProdukMarketplaceDesaRequest;
import id.asqi.idesa.bumdes.model.*;
import id.asqi.idesa.bumdes.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
 class ProdukMarketplaceDesaServiceHelper {

	 final ProdukMarketplaceDesaRepository produkMarketplaceDesaRepository;
	 final JenisVariasiProdukMarketplaceDesaRepository jenisVariasiRepository;
	 final VarianProdukMarketplaceDesaRepository varianRepository;
	 final SatuanRepository satuanRepository;
	 final OpsiVariasiProdukMarketplaceDesaRepository opsiVariasiRepository;
	 final KategoriProdukMarketplaceDesaRepository kategoriRepository;
	 final HargaGrosirRepository hargaGrosirRepository;

	/*saves variants and variants options*/
	 List<JenisVariasiProdukMarketplaceDesa> handleJenisVariasiWithOpsi (ProdukMarketplaceDesa e, ProdukMarketplaceDesaRequest.Create req) {
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

	 List<VarianProdukMarketplaceDesa> handleVarian (ProdukMarketplaceDesa e, ProdukMarketplaceDesaRequest.Create req, List<JenisVariasiProdukMarketplaceDesa> jvList) {
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
			v.setOpsiVariasi(this.getUsedOpsi(varian, ovList));
			v.setIsUtama(varian.getIsUtama());
			v.setTanggalDibuat(LocalDateTime.now());
			varianList.add(v);
		}
		return varianList;
	}

	 List<OpsiVariasiProdukMarketplaceDesa> getUsedOpsi (
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

	 List<OpsiVariasiProdukMarketplaceDesa> getUsedOpsi (
			ProdukMarketplaceDesaRequest.Update.Varian varian,
			List<OpsiVariasiProdukMarketplaceDesa> ovList) {
		return varian.getOpsiVarian().stream()
				.flatMap(jenisOpsi -> ovList.stream()
						.filter(ov ->
								ov.getJenisVariasiProdukMarketplaceDesa().getNama().equals(jenisOpsi.getNamaJenisVariasi())
										&& ov.getNama().equals(jenisOpsi.getNamaOpsiVariasi()))
				)
				.toList();
	}

	 List<HargaGrosir> handleHargaJualGrosir (ProdukMarketplaceDesa e, ProdukMarketplaceDesaRequest.Create req) {
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

	 List<HargaGrosir> handleUpdateHargaJualGrosir(ProdukMarketplaceDesa e, ProdukMarketplaceDesaRequest.Update req) {
		if (req.getHargaJualGrosir() == null || req.getHargaJualGrosir().isEmpty()) {
			return e.getHargaGrosir();
		}

		List<HargaGrosir> existingHargaGrosir = e.getHargaGrosir();
		List<HargaGrosir> newHargaGrosir = new ArrayList<>();
		List<HargaGrosir> updatedHargaGrosir = new ArrayList<>();
		Set<Long> processedIds = new HashSet<>(); // Track processed IDs for efficiency

		for (ProdukMarketplaceDesaRequest.Update.HargaJualGrosir item : req.getHargaJualGrosir()) {
			if (item.isNew()) {
				HargaGrosir hargaGrosir = new HargaGrosir();
				hargaGrosir.setId(Constants.idGenerator());
				hargaGrosir.setProdukMarketplaceDesa(e);
				hargaGrosir.setKuantitasMinimum(item.getMinimalPembelian());
				hargaGrosir.setHarga(item.getHargaJualGrosir());
				newHargaGrosir.add(hargaGrosir);
				continue;
			}

			if (processedIds.contains(item.getId())) {
				continue; // Skip duplicates
			}
			processedIds.add(item.getId());

			/*updates existing*/
			existingHargaGrosir.stream()
					.filter(hg -> Objects.equals(hg.getId(), item.getId()))
					.findFirst()
					.ifPresent(existingHg -> {
						existingHg.setKuantitasMinimum(item.getMinimalPembelian());
						existingHg.setHarga(item.getHargaJualGrosir());
						updatedHargaGrosir.add(existingHg);
					});
		}

		List<Long> toRemoveIds = existingHargaGrosir.stream()
				.map(HargaGrosir::getId)
				.filter(id -> !processedIds.contains(id))
				.toList();

		if (!toRemoveIds.isEmpty())
			hargaGrosirRepository.deleteAllById(toRemoveIds);

		List<HargaGrosir> allHargaGrosir = new ArrayList<>();
		allHargaGrosir.addAll(newHargaGrosir);
		allHargaGrosir.addAll(updatedHargaGrosir);

		return allHargaGrosir;
	}

	/*updates variants and variants options*/
	 List<JenisVariasiProdukMarketplaceDesa> handleUpdateJenisVariasiWithOpsi(ProdukMarketplaceDesa e, ProdukMarketplaceDesaRequest.Update req) {
		if (req.getJenisVarian() == null || req.getJenisVarian().isEmpty()) {
			return e.getJenisVariasi(); // Return existing list if no updates
		}

		List<JenisVariasiProdukMarketplaceDesa> existingJvList = e.getJenisVariasi();
		List<JenisVariasiProdukMarketplaceDesa> newJvList = new ArrayList<>();
		List<JenisVariasiProdukMarketplaceDesa> updatedJvList = new ArrayList<>();
		List<JenisVariasiProdukMarketplaceDesa> toRemoveJvList = new ArrayList<>(existingJvList);
		Set<Long> processedJvIds = new HashSet<>();

		for (ProdukMarketplaceDesaRequest.Update.JenisVarian jenisVarian : req.getJenisVarian()) {
			if (jenisVarian.isNew()) {
				// Create new JenisVariasiProdukMarketplaceDesa and OpsiVariasiProdukMarketplaceDesa
				JenisVariasiProdukMarketplaceDesa newJv = new JenisVariasiProdukMarketplaceDesa();
				newJv.setId(Constants.idGenerator());
				newJv.setProdukMarketplaceDesa(e);
				newJv.setNama(jenisVarian.getNama());

				List<OpsiVariasiProdukMarketplaceDesa> newOvList = new ArrayList<>();
				for (ProdukMarketplaceDesaRequest.Update.OpsiVarian opsiVarian : jenisVarian.getOpsiVarian()) {
					OpsiVariasiProdukMarketplaceDesa newOv = new OpsiVariasiProdukMarketplaceDesa();
					newOv.setId(Constants.idGenerator());
					newOv.setJenisVariasiProdukMarketplaceDesa(newJv);
					newOv.setNama(opsiVarian.getNama());
					newOvList.add(newOv);
				}
				newJv.setOpsiVariasi(newOvList);
				newJvList.add(newJv);
				continue;
			}

			if (processedJvIds.contains(jenisVarian.getId())) {
				continue; // Skip duplicates
			}
			processedJvIds.add(jenisVarian.getId());

			existingJvList.stream()
					.filter(jv -> Objects.equals(jv.getId(), jenisVarian.getId()))
					.findFirst()
					.ifPresent(existingJv -> {
						existingJv.setNama(jenisVarian.getNama());
						updatedJvList.add(existingJv);
						toRemoveJvList.removeIf(jv -> Objects.equals(jv.getId(), jenisVarian.getId()));

						//Handle OpsiVariasiProdukMarketplaceDesa updates and deletes.
						existingJv.getOpsiVariasi().clear();
						List<OpsiVariasiProdukMarketplaceDesa> updatedOpsiList = handleOpsiVariasiUpdates(existingJv, jenisVarian.getOpsiVarian());
						existingJv.setOpsiVariasi(updatedOpsiList);
					});
		}

		// Delete removed JenisVariasiProdukMarketplaceDesa
		List<Long> toRemoveJvIds = toRemoveJvList.stream()
				.map(JenisVariasiProdukMarketplaceDesa::getId)
				.collect(Collectors.toList());

		if (!toRemoveJvIds.isEmpty()) {
			jenisVariasiRepository.deleteAllById(toRemoveJvIds);
		}

		List<JenisVariasiProdukMarketplaceDesa> allJvList = new ArrayList<>();
		allJvList.addAll(newJvList);
		allJvList.addAll(updatedJvList);

		return allJvList;
	}

	 List<OpsiVariasiProdukMarketplaceDesa> handleOpsiVariasiUpdates(JenisVariasiProdukMarketplaceDesa existingJv, List<ProdukMarketplaceDesaRequest.Update.OpsiVarian> updatedOvList) {
		List<OpsiVariasiProdukMarketplaceDesa> existingOvList = existingJv.getOpsiVariasi();
		List<OpsiVariasiProdukMarketplaceDesa> newOvList = new ArrayList<>();
		List<OpsiVariasiProdukMarketplaceDesa> updatedExistingOvList = new ArrayList<>();
		List<OpsiVariasiProdukMarketplaceDesa> toRemoveOvList = new ArrayList<>(existingOvList);
		Set<Long> processedOvIds = new HashSet<>();

		for (ProdukMarketplaceDesaRequest.Update.OpsiVarian updatedOv : updatedOvList) {
			if (updatedOv.isNew()) {
				OpsiVariasiProdukMarketplaceDesa newOv = new OpsiVariasiProdukMarketplaceDesa();
				newOv.setId(Constants.idGenerator());
				newOv.setJenisVariasiProdukMarketplaceDesa(existingJv);
				newOv.setNama(updatedOv.getNama());
				newOvList.add(newOv);
				continue;
			}

			if (processedOvIds.contains(updatedOv.getId())) {
				continue;
			}

			processedOvIds.add(updatedOv.getId());

			existingOvList.stream()
					.filter(ov -> Objects.equals(ov.getId(), updatedOv.getId()))
					.findFirst()
					.ifPresent(existingOv -> {
						existingOv.setNama(updatedOv.getNama());
						updatedExistingOvList.add(existingOv);
						toRemoveOvList.removeIf(ov -> Objects.equals(ov.getId(), updatedOv.getId()));
					});
		}

		List<Long> toRemoveOvIds = toRemoveOvList.stream()
				.map(OpsiVariasiProdukMarketplaceDesa::getId)
				.collect(Collectors.toList());

		if (!toRemoveOvIds.isEmpty()) {
			opsiVariasiRepository.deleteAllById(toRemoveOvIds);
		}

		List<OpsiVariasiProdukMarketplaceDesa> allOvList = new ArrayList<>();
		allOvList.addAll(newOvList);
		allOvList.addAll(updatedExistingOvList);

		return allOvList;
	}

	 List<VarianProdukMarketplaceDesa> handleUpdateVarian(ProdukMarketplaceDesa e, ProdukMarketplaceDesaRequest.Update req, List<JenisVariasiProdukMarketplaceDesa> jvList) {
		if (req.getVarian() == null || req.getVarian().isEmpty()) return e.getVarian();

		List<VarianProdukMarketplaceDesa> existingVarianList = e.getVarian();
		List<VarianProdukMarketplaceDesa> newVarianList = new ArrayList<>();
		List<VarianProdukMarketplaceDesa> updatedVarianList = new ArrayList<>();
		List<VarianProdukMarketplaceDesa> toRemoveVarianList = new ArrayList<>(existingVarianList);
		Set<Long> processedVarianIds = new HashSet<>();

		List<OpsiVariasiProdukMarketplaceDesa> ovList = jvList.stream()
				.flatMap(jv -> jv.getOpsiVariasi().stream())
				.toList();

		for (ProdukMarketplaceDesaRequest.Update.Varian varian : req.getVarian()) {
			if (varian.isNew()) {
				VarianProdukMarketplaceDesa newVarian = new VarianProdukMarketplaceDesa();
				newVarian.setId(Constants.idGenerator());
				newVarian.setProdukMarketplaceDesa(e);
				newVarian.setHargaJualUtama(varian.getHargaJualUtama());
				newVarian.setHargaModal(varian.getHargaModal());
				newVarian.setSatuan(this.getSatuan(varian.getSatuanId()));
				newVarian.setBobotSatuan(varian.getBobotSatuan());
				newVarian.setSku(varian.getSku());
				newVarian.setStok(varian.getStok());
				newVarian.setIsAktif(varian.getIsAktif());
				newVarian.setOpsiVariasi(this.getUsedOpsi(varian, ovList));
				newVarian.setIsUtama(varian.getIsUtama());
				newVarian.setTanggalDibuat(LocalDateTime.now());
				newVarianList.add(newVarian);
				continue;
			}

			if (processedVarianIds.contains(varian.getId())) {
				continue; // Skip duplicates
			}
			processedVarianIds.add(varian.getId());

			existingVarianList.stream()
					.filter(v -> Objects.equals(v.getId(), varian.getId()))
					.findFirst()
					.ifPresent(existingVarian -> {
						existingVarian.setHargaJualUtama(varian.getHargaJualUtama());
						existingVarian.setHargaModal(varian.getHargaModal());
						existingVarian.setSatuan(this.getSatuan(varian.getSatuanId()));
						existingVarian.setBobotSatuan(varian.getBobotSatuan());
						existingVarian.setSku(varian.getSku());
						existingVarian.setStok(varian.getStok());
						existingVarian.setIsAktif(varian.getIsAktif());
						existingVarian.setOpsiVariasi(this.getUsedOpsi(varian, ovList));
						existingVarian.setIsUtama(varian.getIsUtama());
						updatedVarianList.add(existingVarian);
						toRemoveVarianList.removeIf(v -> Objects.equals(v.getId(), varian.getId()));
					});
		}

		List<Long> toRemoveVarianIds = toRemoveVarianList.stream()
				.map(VarianProdukMarketplaceDesa::getId)
				.toList();

		if (!toRemoveVarianIds.isEmpty()) {
			varianRepository.deleteAllById(toRemoveVarianIds);
		}

		List<VarianProdukMarketplaceDesa> allVarianList = new ArrayList<>();
		allVarianList.addAll(newVarianList);
		allVarianList.addAll(updatedVarianList);

		return allVarianList;
	}


	 KategoriProdukMarketplaceDesa getKategori (Long id) {
		return kategoriRepository.findById(id).orElseThrow(() -> new NotFoundEntity(KategoriProdukMarketplaceDesa.class));
	}

	Satuan getSatuan (Long id) {
		return satuanRepository.findById(id).orElseThrow(() -> new NotFoundEntity(Satuan.class));
	}

	 HargaGrosir findHargaGrosir (Long hargaGrosirId){
		return hargaGrosirRepository.findById(hargaGrosirId).orElseThrow(() -> new NotFoundEntity(HargaGrosir.class));
	}


}