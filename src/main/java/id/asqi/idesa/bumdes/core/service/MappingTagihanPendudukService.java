package id.asqi.idesa.bumdes.core.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.http.request.MappingTagihanPendudukRequest;
import id.asqi.idesa.bumdes.model.KategoriTagihanDesa;
import id.asqi.idesa.bumdes.model.MappingTagihanPenduduk;
import id.asqi.idesa.bumdes.model.Penduduk;
import id.asqi.idesa.bumdes.repository.KategoriTagihanDesaRepository;
import id.asqi.idesa.bumdes.repository.MappingTagihanPendudukRepository;
import id.asqi.idesa.bumdes.repository.PendudukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MappingTagihanPendudukService {

	private final MappingTagihanPendudukRepository mappingTagihanPendudukRepository;
	private final PendudukRepository pendudukRepository;
	private final KategoriTagihanDesaRepository kategoriTagihanDesaRepository;

	public Page<MappingTagihanPenduduk> getAll(MappingTagihanPendudukRequest.Filter req, Pageable pageable) {
		return mappingTagihanPendudukRepository.search(
				req.getSearch(),
				req.getIsIncludeDeleted(),
				Auth.getAlamatDesaId(),
				null,
				pageable
		);
	}

	public void create(MappingTagihanPendudukRequest.Create req) {
		MappingTagihanPenduduk e = new MappingTagihanPenduduk();
		Penduduk penduduk = pendudukRepository.findById(req.getPendudukId()).orElseThrow(() -> new RuntimeException("Penduduk not found"));
		Validator.sameDesaCheck(penduduk);
		List<KategoriTagihanDesa> kategoriTagihanDesas = kategoriTagihanDesaRepository.findAllById(req.getKategoriTagihanDesaIds());

		e.setId(Constants.idGenerator());
		e.setPenduduk(penduduk);
		e.setUserBumdes(Auth.getUserBumdes());
		e.setAlamatDesa(Auth.getAlamatDesa());
		e.setKategoriTagihanAktif(kategoriTagihanDesas);
		e.setTanggalDibuat(LocalDateTime.now());
		mappingTagihanPendudukRepository.save(e);
	}

	public void update(MappingTagihanPendudukRequest.Update req) {
		MappingTagihanPenduduk e = mappingTagihanPendudukRepository.findById(req.getId()).orElseThrow(() -> new RuntimeException("Mapping not found"));
		List<KategoriTagihanDesa> kategoriTagihanDesas = kategoriTagihanDesaRepository.findAllById(req.getKategoriTagihanDesaIds());

		e.setKategoriTagihanAktif(kategoriTagihanDesas);
		e.setTanggalDiubah(LocalDateTime.now());
		mappingTagihanPendudukRepository.save(e);
	}

	public void delete(SetDeleteStatusRequest req) {
		MappingTagihanPenduduk mapping = mappingTagihanPendudukRepository.findById(req.getId()).orElseThrow(() -> new RuntimeException("Mapping not found"));
		mapping.setTanggalDihapus(req.getIsDeletedAt());
		mappingTagihanPendudukRepository.save(mapping);
	}
}