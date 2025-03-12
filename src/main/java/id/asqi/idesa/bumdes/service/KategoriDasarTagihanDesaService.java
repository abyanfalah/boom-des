package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchBasicFiltersRequest;
import id.asqi.idesa.bumdes.core.http.request.ToggleRequest;
import id.asqi.idesa.bumdes.http.request.KategoriDasarTagihanDesaRequest;
import id.asqi.idesa.bumdes.model.KategoriDasarTagihanDesa;
import id.asqi.idesa.bumdes.repository.KategoriDasarTagihanDesaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KategoriDasarTagihanDesaService {

	private final KategoriDasarTagihanDesaRepository kategoriDasarTagihanDesaRepository;

	public Page<KategoriDasarTagihanDesa> getAll(KategoriDasarTagihanDesaRequest.Filter req) {
		return kategoriDasarTagihanDesaRepository.search(
				req.getSearch(),
				req.getIsAktif(),
				req.getIsIncludeDeleted(),
				req.getPagination()
		);
	}

	public List<KategoriDasarTagihanDesa> getAll(SearchBasicFiltersRequest req) {
		return kategoriDasarTagihanDesaRepository.search(
				req.getSearch(),
				null,
				false
		);
	}

	public void create(KategoriDasarTagihanDesaRequest.Create req) {
		KategoriDasarTagihanDesa e = new KategoriDasarTagihanDesa();
		e.setId(Constants.idGenerator());
		e.setNama(req.getNama());
		e.setIsPajak(req.getIsPajak());
		e.setSiklusBayar(req.getSiklusBayar());
		e.setIsAktif(true);
		kategoriDasarTagihanDesaRepository.save(e);
	}

	public void update(KategoriDasarTagihanDesaRequest.Update req) {
		KategoriDasarTagihanDesa e = this.findById(req.getId());
		e.setNama(req.getNama());
		e.setIsPajak(req.getIsPajak());
		e.setSiklusBayar(req.getSiklusBayar());
		e.setIsAktif(req.getIsAktif());
		kategoriDasarTagihanDesaRepository.save(e);
	}

	public void delete(IdNumberRequest req) {
		KategoriDasarTagihanDesa e = this.findById(req.getId());
		kategoriDasarTagihanDesaRepository.delete(e);
	}

	public void toggleStatus (ToggleRequest.AktifStatus req) {
		KategoriDasarTagihanDesa e = this.findById(req.getId());
		e.setIsAktif(req.getIsAktif());
		kategoriDasarTagihanDesaRepository.save(e);

	}

	private KategoriDasarTagihanDesa findById(Long id){
		return kategoriDasarTagihanDesaRepository.findById(id).orElseThrow(() -> new NotFoundEntity(KategoriDasarTagihanDesa.class));
	}
}