package id.asqi.idesa.bumdes.core.service;

import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.http.request.PendudukRequest;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.model.Penduduk;
import id.asqi.idesa.bumdes.repository.PendudukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PendudukService {
	private final PendudukRepository pendudukRepository;
	public Page<Penduduk> getAll(PendudukRequest.Filter req) {
		return pendudukRepository.search(
				req.getSearch(),
				req.getPagination()
				);
	}

	public Penduduk create(PendudukRequest.Create req) {
		Penduduk penduduk = new Penduduk();
		penduduk.setNik(req.getNik());
		penduduk.setNama(req.getNama());
		penduduk.setAlamatDesa(Auth.getAlamatDesa());
//		penduduk.setTanggalDibuat(LocalDateTime.now());
		return pendudukRepository.save(penduduk);
	}

	public Penduduk update(PendudukRequest.Update req) {
		Penduduk penduduk = this.findById(req.getId());
		penduduk.setNik(req.getNik());
		penduduk.setNama(req.getNama());
//		penduduk.setTanggalDiubah(LocalDateTime.now());
		return pendudukRepository.save(penduduk);
	}

	public void delete(SetDeleteStatusRequest req) {
		Penduduk penduduk = this.findById(req.getId());
//		penduduk.setTanggalDihapus(req.getIsDeletedAt());
		pendudukRepository.save(penduduk);
	}

	public Penduduk findById(Long id) {
		return pendudukRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Penduduk not found"));
	}
}