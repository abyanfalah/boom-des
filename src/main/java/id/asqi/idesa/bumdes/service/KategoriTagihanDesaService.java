package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchBasicFiltersRequest;
import id.asqi.idesa.bumdes.core.http.request.ToggleRequest;
import id.asqi.idesa.bumdes.http.request.KategoriTagihanDesaRequest;
import id.asqi.idesa.bumdes.model.KategoriTagihanDesa;
import id.asqi.idesa.bumdes.repository.KategoriTagihanDesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KategoriTagihanDesaService {

	private final KategoriTagihanDesaRepository kategoriTagihanDesaRepository;

	public Page<KategoriTagihanDesa> getAll(KategoriTagihanDesaRequest.Filter req) {
		return kategoriTagihanDesaRepository.search(
				req.getSearch(),
				req.getIsAktif(),
				req.getIsIncludeDeleted(),
				req.getPagination()
		);
	}

	public List<KategoriTagihanDesa> getAll(SearchBasicFiltersRequest req) {
		return kategoriTagihanDesaRepository.search(
				req.getSearch(),
				null,
				false
		);
	}

	public void create(KategoriTagihanDesaRequest.Create req) {
		KategoriTagihanDesa e = new KategoriTagihanDesa();
		e.setId(Constants.idGenerator());
		e.setNama(req.getNama());
		e.setIsPajak(req.getIsPajak());
		e.setSiklusBayar(req.getSiklusBayar());
		e.setIsAktif(true);
		kategoriTagihanDesaRepository.save(e);
	}

	public void update(KategoriTagihanDesaRequest.Update req) {
		KategoriTagihanDesa e = this.findById(req.getId());
		e.setNama(req.getNama());
		e.setIsPajak(req.getIsPajak());
		e.setSiklusBayar(req.getSiklusBayar());
		e.setIsAktif(req.getIsAktif());
		kategoriTagihanDesaRepository.save(e);
	}

	public void delete(IdNumberRequest req) {
		KategoriTagihanDesa e = this.findById(req.getId());
		kategoriTagihanDesaRepository.delete(e);
	}

	public void toggleStatus (ToggleRequest.AktifStatus req) {
		KategoriTagihanDesa e = this.findById(req.getId());
		e.setIsAktif(req.getIsAktif());
		kategoriTagihanDesaRepository.save(e);

	}

	private KategoriTagihanDesa findById(Long id){
		return kategoriTagihanDesaRepository.findById(id).orElseThrow(() -> new NotFoundEntity(KategoriTagihanDesa.class));
	}
}