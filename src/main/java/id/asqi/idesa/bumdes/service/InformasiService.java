package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.component.exception.InvalidOperationException;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.service.S3Storage;
import id.asqi.idesa.bumdes.enums.FolderName;
import id.asqi.idesa.bumdes.http.request.InformasiRequest;
import id.asqi.idesa.bumdes.model.DokumenInformasi;
import id.asqi.idesa.bumdes.model.GambarInformasi;
import id.asqi.idesa.bumdes.model.Informasi;
import id.asqi.idesa.bumdes.repository.DokumenInformasiRepository;
import id.asqi.idesa.bumdes.repository.GambarInformasiRepository;
import id.asqi.idesa.bumdes.repository.InformasiRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InformasiService {
	private final InformasiRepository informasiRepository;
	private final GambarInformasiRepository gambarInformasiRepository;
	private final DokumenInformasiRepository dokumenInformasiRepository;

	private final S3Storage s3Storage;

	public Page<Informasi> getAll (SearchPaginationRequest req) {
		return informasiRepository.search(
				req.getSearch(),
				Auth.id(),
				req.getPagination()
		);
	}

	@Transactional
	public void create (InformasiRequest.Create req) throws Exception {
		Informasi e = new Informasi();
		e.setId(Constants.idGenerator());
		e.setJudul(req.getJudul());
		e.setIsi(req.getIsi());
		e.setUserBumdes(Auth.getUserBumdes());
		e.setTanggalDibuat(LocalDateTime.now());

		e = informasiRepository.save(e);

//		this.saveDokumen(req.getDokumen(), e);
//		this.saveGambar(req.getGambar(), e);
	}

	@Transactional
	public void update (InformasiRequest.Update req) {
		Informasi e = informasiRepository.findById(req.getId()).orElseThrow(() -> new NotFoundEntity(Informasi.class));

		this.validateOwner(e);

		e.setJudul(req.getJudul());
		e.setIsi(req.getIsi());
		e.setTanggalDiubah(LocalDateTime.now());

		/*for the docs and images, need to know the update mechanism first*/

		informasiRepository.save(e);
	}

	public void delete (IdNumberRequest req) {
		Informasi e = informasiRepository.findById(req.getId()).orElseThrow(() -> new NotFoundEntity(Informasi.class));

		validateOwner(e);

		if(! Objects.equals(e.getUserBumdes().getId(), Auth.id())) {
			throw new InvalidOperationException("Tidak bisa menghapus informasi orang lain");
		}

		informasiRepository.delete(e);
	}

	private void saveDokumen (List<MultipartFile> files, Informasi e) throws Exception {
		List<DokumenInformasi> dokumen = new ArrayList<>();
		for (MultipartFile file : files) {
			DokumenInformasi d = new DokumenInformasi();
			d.setId(Constants.idGenerator());
			d.setInformasi(e);
			d.setTanggalDibuat(LocalDateTime.now());
			d.setUrl(s3Storage.uploadFile(FolderName.DOKUMEN_INFORMASI, file));
			dokumen.add(d);
		}
		dokumenInformasiRepository.saveAll(dokumen);
	}

	private void saveGambar (List<InformasiRequest.Create.GambarInformasiReq> reqs, Informasi e) throws Exception {
		List<GambarInformasi> dokumen = new ArrayList<>();
		for (InformasiRequest.Create.GambarInformasiReq req : reqs) {
			MultipartFile file = req.getFile();
			GambarInformasi d = new GambarInformasi();
			d.setId(Constants.idGenerator());
			d.setInformasi(e);
			d.setTanggalDibuat(LocalDateTime.now());
			d.setIsCover(req.getIsCover());
			d.setUrl(s3Storage.uploadFile(FolderName.GAMBAR_INFORMASI, file));
			dokumen.add(d);
		}
		gambarInformasiRepository.saveAll(dokumen);
	}

	private void validateOwner(Informasi e){
		if(! Objects.equals(e.getUserBumdes().getId(), Auth.id())){
			throw new InvalidOperationException("Informasi ini bukan milik orang Anda.");
		}
	}


}