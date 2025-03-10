package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.SearchBasicFiltersRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.http.request.KategoriKomoditasRequest;
import id.asqi.idesa.bumdes.model.KategoriKomoditas;
import id.asqi.idesa.bumdes.repository.KategoriKomoditasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KategoriKomoditasService {
	private final KategoriKomoditasRepository kategoriKomoditasRepository;

	public List<KategoriKomoditas>  getList (SearchBasicFiltersRequest req) {
		return kategoriKomoditasRepository.search(
				req.getSearch(),
				false
		);
	}

	public Page<KategoriKomoditas> getAll (SearchPaginationRequest req) {
		return kategoriKomoditasRepository.search(
				req.getSearch(),
				req.getIsIncludeDeleted(),
				req.getPagination()
		);
	}

	public void create (KategoriKomoditasRequest.Create req) throws Exception {
		KategoriKomoditas e = new KategoriKomoditas();
		e.setId(Constants.idGenerator());
		e.setNama(req.getNama());
		e.setDeskripsi(req.getDeskripsi());
		e.setTanggalDibuat(LocalDateTime.now());
		kategoriKomoditasRepository.save(e);
	}

	public void update (KategoriKomoditasRequest.Update req) {
		KategoriKomoditas e = this.findById(req.getId());
		e.setNama(req.getNama());
		e.setDeskripsi(req.getDeskripsi());
		e.setTanggalDiubah(LocalDateTime.now());
		kategoriKomoditasRepository.save(e);
	}

	public void softDelete (SetDeleteStatusRequest req) {
		KategoriKomoditas e = this.findById(req.getId());

		e.setTanggalDihapus(
				req.getIsDeleted() ?
						LocalDateTime.now()
						: null
		);

		kategoriKomoditasRepository.save(e);
	}

	private KategoriKomoditas findById (Long id) {
		return kategoriKomoditasRepository.findById(id).orElseThrow(() -> new NotFoundEntity(KategoriKomoditas.class));
	}
}