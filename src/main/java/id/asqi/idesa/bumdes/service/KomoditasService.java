package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.core.service.Validator;
import id.asqi.idesa.bumdes.http.request.KomoditasRequest;
import id.asqi.idesa.bumdes.model.KategoriKomoditas;
import id.asqi.idesa.bumdes.model.Komoditas;
import id.asqi.idesa.bumdes.repository.KategoriKomoditasRepository;
import id.asqi.idesa.bumdes.repository.KomoditasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KomoditasService {
	private final KomoditasRepository komoditasRepository;
	private final KategoriKomoditasRepository kategoriKomoditasRepository;

	private KategoriKomoditas findKategoriById (Long id) {
		return kategoriKomoditasRepository.findById(id).orElseThrow(() -> new NotFoundEntity(KategoriKomoditas.class));
	}

	public Page<Komoditas> getAll (SearchPaginationRequest req) {
		return komoditasRepository.search(
				req.getSearch(),
				req.getIsIncludeDeleted(),
				req.getPagination()
		);
	}

	public void create (KomoditasRequest.Create req) throws Exception {
		KategoriKomoditas kategori = this.findKategoriById(req.getKategoriKomoditasId());
		Validator.deletionCheck(kategori.getTanggalDihapus(), KategoriKomoditas.class);

		Komoditas e = new Komoditas();
		e.setId(Constants.idGenerator());
		e.setNama(req.getNama());
		e.setKategoriKomoditas(kategori);
		e.setTanggalDibuat(LocalDateTime.now());
		komoditasRepository.save(e);
	}

	public void update (KomoditasRequest.Update req) {
		Komoditas e = this.findById(req.getId());

		KategoriKomoditas kategori = this.findKategoriById(req.getKategoriKomoditasId());
		Validator.deletionCheck(kategori.getTanggalDihapus(), KategoriKomoditas.class);

		e.setNama(req.getNama());
		e.setKategoriKomoditas(kategori);
		e.setTanggalDiubah(LocalDateTime.now());
		komoditasRepository.save(e);
	}

	public void softDelete (SetDeleteStatusRequest req) {
		Komoditas e = this.findById(req.getId());

		e.setTanggalDihapus(
				req.getIsDeleted() ?
						LocalDateTime.now()
						: null
		);

		komoditasRepository.save(e);
	}

	private Komoditas findById (Long id) {
		return komoditasRepository.findById(id).orElseThrow(() -> new NotFoundEntity(Komoditas.class));
	}
}