package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.SearchBasicFiltersRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.http.request.KategoriPostinganForumBumdesRequest;
import id.asqi.idesa.bumdes.model.KategoriPostinganForumBumdes;
import id.asqi.idesa.bumdes.repository.KategoriPostinganForumBumdesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KategoriPostinganForumBumdesService {

	private final KategoriPostinganForumBumdesRepository kategoriPostinganForumBumdesRepository;

	public List<KategoriPostinganForumBumdes> getList(SearchBasicFiltersRequest req) {
		return kategoriPostinganForumBumdesRepository.search(
				req.getSearch(),
				false
		);
	}

	public Page<KategoriPostinganForumBumdes> getAll(SearchPaginationRequest req) {
		return kategoriPostinganForumBumdesRepository.search(
				req.getSearch(),
				req.getIsIncludeDeleted(),
				req.getPagination()
		);
	}

	public void create(KategoriPostinganForumBumdesRequest.Create req) throws Exception {
		KategoriPostinganForumBumdes e = new KategoriPostinganForumBumdes();
		e.setId(Constants.idGenerator());
		e.setNama(req.getNama());
		kategoriPostinganForumBumdesRepository.save(e);
	}

	public void update(KategoriPostinganForumBumdesRequest.Update req) {
		KategoriPostinganForumBumdes e = this.findById(req.getId());
		e.setNama(req.getNama());
		kategoriPostinganForumBumdesRepository.save(e);
	}

	public void softDelete (SetDeleteStatusRequest req) {
		KategoriPostinganForumBumdes e = this.findById(req.getId());

		e.setTanggalDihapus(
				req.getIsDeleted() ?
						LocalDateTime.now()
						: null
		);

		kategoriPostinganForumBumdesRepository.save(e);
	}

	private KategoriPostinganForumBumdes findById(Long id) {
		return kategoriPostinganForumBumdesRepository.findById(id).orElseThrow(() -> new NotFoundEntity(KategoriPostinganForumBumdes.class));
	}
}