package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.*;
import id.asqi.idesa.bumdes.enums.JenisPenagihan;
import id.asqi.idesa.bumdes.http.request.KategoriTagihanDesaRequest;
import id.asqi.idesa.bumdes.model.KategoriDasarTagihanDesa;
import id.asqi.idesa.bumdes.model.KategoriTagihanDesa;
import id.asqi.idesa.bumdes.model.Satuan;
import id.asqi.idesa.bumdes.repository.KategoriDasarTagihanDesaRepository;
import id.asqi.idesa.bumdes.repository.KategoriTagihanDesaRepository;
import id.asqi.idesa.bumdes.repository.SatuanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KategoriTagihanDesaService {

	private final KategoriTagihanDesaRepository kategoriTagihanDesaRepository;
	private final KategoriDasarTagihanDesaRepository kategoriDasarTagihanDesaRepository;
	private final SatuanRepository satuanRepository;

	public Page<KategoriTagihanDesa> getAll(SearchPaginationRequest req) {
//		Long asdf = Auth

		return kategoriTagihanDesaRepository.search(
				req.getSearch(),
				req.getIsAktif(),
				req.getIsIncludeDeleted(),
//				Auth.getAlamatDesaId(),
				null,
				req.getPagination()
		);
	}

	public List<KategoriTagihanDesa> getAll(SearchBasicFiltersRequest req) {
		return kategoriTagihanDesaRepository.search(
				req.getSearch(),
				null,
				false,
				Auth.getAlamatDesaId()
		);
	}

	public void create(KategoriTagihanDesaRequest.Create req) {
		KategoriTagihanDesa e = new KategoriTagihanDesa();
		KategoriDasarTagihanDesa kategoriDasar = this.findKategoriById(req.getKategoriDasarTagihanDesaId());
		Satuan satuan = req.getSatuanId() == null ? null : this.findSatuanById(req.getSatuanId());

		e.setId(Constants.idGenerator());
		e.setKategoriDasarTagihanDesa(kategoriDasar);
		e.setNama(req.getNama());
		e.setNominalTagihanBulanan(req.getNominalTagihanBulanan());
		e.setBiayaAdminAplikasi(req.getBiayaAdminAplikasi());
		e.setBiayaAdminPengguna(req.getBiayaAdminPengguna());
		e.setBiayaAdminMitraBumdes(req.getBiayaAdminMitraBumdes());
		e.setDenda(req.getDenda());
		e.setSatuanDenda(req.getSatuanDenda());
		e.setTanggalPenagihan(req.getTanggalPenagihan());
		e.setUserBumdes(Auth.getUserBumdes());
		e.setAlamatDesa(Auth.getAlamatDesa());
		e.setTanggalJatuhTempo(req.getTanggalJatuhTempo());
		e.setTanggalPenagihan(req.getTanggalPenagihan());
		e.setJenisPenagihan(req.getJenisPenagihan());
		e.setSatuan(satuan);
		e.setHargaPerSatuan(req.getHargaPerSatuan());

		e.setIsAktif(req.getIsAktif());
		e.setTanggalDibuat(LocalDateTime.now());
		kategoriTagihanDesaRepository.save(e);
	}

	public void update(KategoriTagihanDesaRequest.Update req) {
		KategoriTagihanDesa e = this.findById(req.getId());
		KategoriDasarTagihanDesa kategoriDasar = this.findKategoriById(req.getKategoriDasarTagihanDesaId());
		Satuan satuan = req.getSatuanId() == null ? null : this.findSatuanById(req.getSatuanId());

		e.setKategoriDasarTagihanDesa(kategoriDasar);
		e.setNama(req.getNama());
		e.setNominalTagihanBulanan(req.getNominalTagihanBulanan());
		e.setBiayaAdminAplikasi(req.getBiayaAdminAplikasi());
		e.setBiayaAdminPengguna(req.getBiayaAdminPengguna());
		e.setBiayaAdminMitraBumdes(req.getBiayaAdminMitraBumdes());
		e.setDenda(req.getDenda());
		e.setSatuanDenda(req.getSatuanDenda());
		e.setTanggalPenagihan(req.getTanggalPenagihan());
		e.setUserBumdes(Auth.getUserBumdes());
		e.setAlamatDesa(Auth.getAlamatDesa());
		e.setTanggalJatuhTempo(req.getTanggalJatuhTempo());
		e.setTanggalPenagihan(req.getTanggalPenagihan());
		e.setJenisPenagihan(req.getJenisPenagihan());
		e.setHargaPerSatuan(req.getHargaPerSatuan());
		e.setSatuan(satuan);
		e.setHargaPerSatuan(req.getHargaPerSatuan());

		e.setIsAktif(req.getIsAktif());
		e.setTanggalDibuat(LocalDateTime.now());
		kategoriTagihanDesaRepository.save(e);
	}

	public void delete(SetDeleteStatusRequest req) {
		KategoriTagihanDesa e = this.findById(req.getId());
		e.setTanggalDihapus(req.getIsDeletedAt());
		kategoriTagihanDesaRepository.save(e);
	}

	public void toggleStatus (ToggleRequest.AktifStatus req) {
		KategoriTagihanDesa e = this.findById(req.getId());
		e.setIsAktif(req.getIsAktif());
		kategoriTagihanDesaRepository.save(e);
	}

	private KategoriTagihanDesa findById(Long id){
		return kategoriTagihanDesaRepository.findById(id).orElseThrow(() -> new NotFoundEntity(KategoriTagihanDesa.class));
	}

	private KategoriDasarTagihanDesa findKategoriById(Long id){
		return kategoriDasarTagihanDesaRepository.findById(id).orElseThrow(() -> new NotFoundEntity(KategoriDasarTagihanDesa.class));
	}

	private Satuan findSatuanById(Long id){
		return satuanRepository.findById(id).orElseThrow(() -> new NotFoundEntity(Satuan.class));

	}
}