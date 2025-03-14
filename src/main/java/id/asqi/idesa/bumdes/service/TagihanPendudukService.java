package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.core.http.request.TagihanPendudukRequest;
import id.asqi.idesa.bumdes.model.KategoriTagihanDesa;
import id.asqi.idesa.bumdes.model.MappingTagihanPenduduk;
import id.asqi.idesa.bumdes.model.Penduduk;
import id.asqi.idesa.bumdes.model.TagihanPenduduk;
import id.asqi.idesa.bumdes.repository.KategoriTagihanDesaRepository;
import id.asqi.idesa.bumdes.repository.MappingTagihanPendudukRepository;
import id.asqi.idesa.bumdes.repository.PendudukRepository;
import id.asqi.idesa.bumdes.repository.TagihanPendudukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TagihanPendudukService {

	private final TagihanPendudukRepository tagihanPendudukRepository;
	private final PendudukRepository pendudukRepository;
	private final KategoriTagihanDesaRepository kategoriTagihanDesaRepository;
	private final MappingTagihanPendudukRepository mappingTagihanPendudukRepository;

	public Page<TagihanPenduduk> getAll (TagihanPendudukRequest.Filter req) {
		Pageable pageable = req.getPagination();
		return tagihanPendudukRepository.search(
				req.getSearch(),
				Auth.getAlamatDesa().getId(),
				req.getPendudukId(),
				req.getKategoriTagihanId(),
				pageable
		);
	}

	public void create (TagihanPendudukRequest.Create req) {
		KategoriTagihanDesa kategori = this.findKategoriTagihanById(req.getKategoriTagihanDesaId());
		MappingTagihanPenduduk mappingTagihanPenduduk  = this.findMappingTagihanById(req.getMappingTagihanId());
		Penduduk penduduk = mappingTagihanPenduduk.getPenduduk();

		TagihanPenduduk tagihanPenduduk = new TagihanPenduduk();
		tagihanPenduduk.setId(Constants.idGenerator());
		tagihanPenduduk.setMappingTagihan(mappingTagihanPenduduk);
		tagihanPenduduk.setPenduduk(penduduk);
		tagihanPenduduk.setKategoriTagihanDesa(kategori);
		tagihanPenduduk.setTahun(req.getTahun());
		tagihanPenduduk.setBulan(req.getBulan());
		tagihanPenduduk.setPemakaianSatuan(req.getPemakaianSatuan());
		tagihanPenduduk.setTotalPotongan(req.getTotalPotongan());
		tagihanPenduduk.setTotalTagihan(req.getTotalTagihan());
		tagihanPenduduk.setUserBumdes(Auth.getUserBumdes());
		tagihanPenduduk.setAlamatDesa(Auth.getAlamatDesa());
		tagihanPenduduk.setTanggalDibuat(LocalDateTime.now());
		tagihanPendudukRepository.save(tagihanPenduduk);
	}

	public void update (TagihanPendudukRequest.Update req) {
		TagihanPenduduk tagihanPenduduk = this.findById(req.getId());
		KategoriTagihanDesa kategori = this.findKategoriTagihanById(req.getKategoriTagihanDesaId());

		tagihanPenduduk.setKategoriTagihanDesa(kategori);
		tagihanPenduduk.setTahun(req.getTahun());
		tagihanPenduduk.setBulan(req.getBulan());
		tagihanPenduduk.setPemakaianSatuan(req.getPemakaianSatuan());
		tagihanPenduduk.setTotalPotongan(req.getTotalPotongan());
		tagihanPenduduk.setTotalTagihan(req.getTotalTagihan());
		tagihanPenduduk.setTanggalDiubah(LocalDateTime.now());
		tagihanPendudukRepository.save(tagihanPenduduk);
	}

	public void delete (SetDeleteStatusRequest req) {
		TagihanPenduduk tagihanPenduduk = this.findById(req.getId());
		tagihanPenduduk.setTanggalDihapus(req.getIsDeletedAt());
		tagihanPendudukRepository.save(tagihanPenduduk);
	}

	private TagihanPenduduk findById (Long id) {
		return tagihanPendudukRepository.findById(id).orElseThrow(() -> new NotFoundEntity(TagihanPenduduk.class));
	}

	private MappingTagihanPenduduk findMappingTagihanById (Long id) {
		return mappingTagihanPendudukRepository.findById(id).orElseThrow(() -> new NotFoundEntity(MappingTagihanPenduduk.class));
	}

	private Penduduk findPendudukById (Long id) {
		return pendudukRepository.findById(id).orElseThrow(() -> new NotFoundEntity(Penduduk.class));
	}

	private KategoriTagihanDesa findKategoriTagihanById(Long id){
		return kategoriTagihanDesaRepository.findById(id).orElseThrow(() -> new NotFoundEntity(KategoriTagihanDesa.class));
	}
}