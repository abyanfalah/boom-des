package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.component.exception.InvalidOperationException;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.service.S3Storage;
import id.asqi.idesa.bumdes.enums.FolderName;
import id.asqi.idesa.bumdes.http.request.InformasiTokoDesaRequest;
import id.asqi.idesa.bumdes.model.InformasiTokoDesa;
import id.asqi.idesa.bumdes.model.MetodePengiriman;
import id.asqi.idesa.bumdes.repository.InformasiTokoDesaRepository;
import id.asqi.idesa.bumdes.repository.MetodePengirimanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InformasiTokoDesaService {
	private final InformasiTokoDesaRepository informasiTokoDesaRepository;
	private final MetodePengirimanRepository metodePengirimanRepository;

	private final S3Storage s3Storage;

	public InformasiTokoDesa get () {
		return informasiTokoDesaRepository.findByAlamatDesaId(Auth.getAlamatDesaId()).orElse(null);
	}

	public void create (InformasiTokoDesaRequest.Create req) throws Exception {
		this.validateDesaHasNoInformasiTokoDesa();

		String urlFotoProfil = s3Storage.uploadFile(FolderName.FOTO_PROFIL_TOKO_DESA, req.getFotoProfil());
		List<MetodePengiriman> listMetodePengiriman = metodePengirimanRepository.findByIdList(req.getListMetodePengirimanId());

		InformasiTokoDesa e = new InformasiTokoDesa();
		e.setId(Constants.idGenerator());
		e.setNama(req.getNama());
		e.setNoTelpon(req.getNoTelpon());
		e.setAlamatLengkap(req.getAlamatLengkap());
		e.setLatitude(req.getLatitude());
		e.setLongitude(req.getLongitude());
		e.setUrlFotoProfil(urlFotoProfil);
		e.setMetodePengiriman(listMetodePengiriman);
		e.setAlamatDesa(Auth.getAlamatDesa());

		informasiTokoDesaRepository.save(e);
	}

	public void update (InformasiTokoDesaRequest.Update req) throws Exception {
		InformasiTokoDesa e = informasiTokoDesaRepository.findByAlamatDesaId(Auth.getAlamatDesaId()).orElseThrow(() -> new NotFoundEntity(InformasiTokoDesa.class));

		String urlFotoProfil = s3Storage.uploadFile(FolderName.FOTO_PROFIL_TOKO_DESA, req.getFotoProfil());
		List<MetodePengiriman> listMetodePengiriman = metodePengirimanRepository.findByIdList(req.getListMetodePengirimanId());

		e.setNama(req.getNama());
		e.setNoTelpon(req.getNoTelpon());
		e.setAlamatLengkap(req.getAlamatLengkap());
		e.setLatitude(req.getLatitude());
		e.setLongitude(req.getLongitude());
		e.setUrlFotoProfil(urlFotoProfil);
		e.setMetodePengiriman(listMetodePengiriman);
		e.setAlamatDesa(Auth.getAlamatDesa());

		informasiTokoDesaRepository.save(e);
	}

	private void validateDesaOwner(InformasiTokoDesa informasiTokoDesa) {
		if (!Objects.equals(informasiTokoDesa.getAlamatDesa().getId(), Auth.getAlamatDesaId())) {
			throw new InvalidOperationException("Anda tidak memiliki akses ke data ini");
		}
	}

	private void validateDesaHasNoInformasiTokoDesa () {
		if (informasiTokoDesaRepository.hasInfoTokoByDesaId(Auth.getAlamatDesaId())) {
			throw new InvalidOperationException("Informasi toko desa sudah ada");
		}
	}
}