package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.http.request.MitraRequest;
import id.asqi.idesa.bumdes.model.JabatanMitra;
import id.asqi.idesa.bumdes.model.JenisMitra;
import id.asqi.idesa.bumdes.model.Mitra;
import id.asqi.idesa.bumdes.model.Penduduk;
import id.asqi.idesa.bumdes.repository.JabatanMitraRepository;
import id.asqi.idesa.bumdes.repository.JenisMitraRepository;
import id.asqi.idesa.bumdes.repository.MitraRepository;
import id.asqi.idesa.bumdes.repository.PendudukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MitraService {
	private final MitraRepository mitraRepository;
	private final PendudukRepository pendudukRepository;
	private final JabatanMitraRepository jabatanMitraRepository;
	private final JenisMitraRepository jenisMitraRepository;
	public Page<Mitra> getAll (SearchPaginationRequest req) {
		return mitraRepository.search(
				req.getSearch(),
				req.getPagination()
		);
	}

	public void create (MitraRequest.Create req) throws Exception {
		JenisMitra jm = jenisMitraRepository.findById(req.getJenisMitraId()).orElseThrow(() -> new NotFoundEntity(JenisMitra.class));
		Penduduk p = pendudukRepository.findById(req.getPendudukId()).orElseThrow(() -> new NotFoundEntity(Penduduk.class));
		JabatanMitra jabatanMitra = jabatanMitraRepository.findById(req.getJabatanMitraId()).orElseThrow(() -> new NotFoundEntity(JabatanMitra.class));


		Mitra e = new Mitra();
		e.setId(Constants.idGenerator());
		e.setPenduduk(p);
		e.setJenisMitra(jm);
		e.setIsAktif(req.getIsAktif());
		e.setSaldo(BigDecimal.ZERO);
		e.setAlamatDesa(p.getAlamatDesa());
		e.setJabatanMitra(jabatanMitra);
		e.setTanggalDibuat(LocalDateTime.now());

		mitraRepository.save(e);
	}

	public void update (MitraRequest.Update req) {
		Mitra e = mitraRepository.findById(req.getId()).orElseThrow(() -> new NotFoundEntity(Mitra.class));
		JenisMitra jm = jenisMitraRepository.findById(req.getJenisMitraId()).orElseThrow(() -> new NotFoundEntity(JenisMitra.class));

		e.setJenisMitra(jm);
		e.setIsAktif(req.getIsAktif());
		e.setTanggalDiubah(LocalDateTime.now());

		mitraRepository.save(e);
	}

	public void setStatus (MitraRequest.SetStatus req) {
		Mitra e = mitraRepository.findById(req.getId()).orElseThrow(() -> new NotFoundEntity(Mitra.class));

		e.setIsAktif(req.getIsAktif());
		e.setTanggalDiubah(LocalDateTime.now());

		mitraRepository.save(e);
	}




}