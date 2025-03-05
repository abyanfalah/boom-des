package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.component.exception.InvalidOperationException;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.service.S3Storage;
import id.asqi.idesa.bumdes.http.request.BeritaBumdesRequest;
import id.asqi.idesa.bumdes.model.BeritaBumdes;
import id.asqi.idesa.bumdes.model.KategoriBeritaBumdes;
import id.asqi.idesa.bumdes.repository.BeritaBumdesRepository;
import id.asqi.idesa.bumdes.repository.KategoriBeritaBumdesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BeritaBumdesService {
	private final BeritaBumdesRepository beritaBumdesRepository;
	private final KategoriBeritaBumdesRepository kategoriBeritaBumdesRepository;
	private final S3Storage s3Storage;

	public Page<BeritaBumdes> getAll (SearchPaginationRequest req) {
		return beritaBumdesRepository.search(
				req.getSearch(),
				Auth.id(),
				req.getPagination()
		);
	}

	@Transactional
	public void create (BeritaBumdesRequest.Create req) throws Exception {

		KategoriBeritaBumdes kategoriBeritaBumdes = kategoriBeritaBumdesRepository.findById(req.getKategoriBeritaBumdesId()).orElseThrow(() -> new NotFoundEntity(KategoriBeritaBumdes.class));

		BeritaBumdes e = new BeritaBumdes();
		e.setId(Constants.idGenerator());
		e.setJudul(req.getJudul());
		e.setIsi(req.getIsi());
		e.setUserBumdes(Auth.getUserBumdes());
		e.setKategoriBeritaBumdes(kategoriBeritaBumdes);
		e.setAlamatDesa(Auth.getAlamatDesa());
		e.setIsDitayangkan(false);
		e.setKataKunci(req.getKataKunci());
		e.setTanggalDibuat(LocalDateTime.now());

		beritaBumdesRepository.save(e);
	}

	@Transactional
	public void update (BeritaBumdesRequest.Update req) {
		BeritaBumdes e = beritaBumdesRepository.findById(req.getId()).orElseThrow(() -> new NotFoundEntity(BeritaBumdes.class));

		KategoriBeritaBumdes kategoriBeritaBumdes = kategoriBeritaBumdesRepository.findById(req.getKategoriBeritaBumdesId()).orElseThrow(() -> new NotFoundEntity(KategoriBeritaBumdes.class));

		this.validateOwner(e);

		e.setJudul(req.getJudul());
		e.setIsi(req.getIsi());
		e.setKataKunci(req.getKataKunci());
		e.setIsDitayangkan(req.getIsDitayangkan());
		e.setKategoriBeritaBumdes(kategoriBeritaBumdes);
		e.setTanggalDiubah(LocalDateTime.now());

		beritaBumdesRepository.save(e);
	}

	public void delete (IdNumberRequest req) {
		BeritaBumdes e = beritaBumdesRepository.findById(req.getId()).orElseThrow(() -> new NotFoundEntity(BeritaBumdes.class));

		validateOwner(e);

		if(! Objects.equals(e.getUserBumdes().getId(), Auth.id())) {
			throw new InvalidOperationException("Tidak dapat menghapus Berita Bumdes yang bukan milik Anda.");
		}

		beritaBumdesRepository.delete(e);
	}

	private void validateOwner(BeritaBumdes e){
		if(! Objects.equals(e.getUserBumdes().getId(), Auth.id())){
			throw new InvalidOperationException("Berita Bumdes ini bukan milik Anda.");
		}
	}


}