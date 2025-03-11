package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.component.exception.InvalidOperationException;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.service.S3Storage;
import id.asqi.idesa.bumdes.enums.FolderName;
import id.asqi.idesa.bumdes.http.request.ProdukGrosirRequest;
import id.asqi.idesa.bumdes.model.GambarProdukGrosir;
import id.asqi.idesa.bumdes.model.KategoriProdukGrosir;
import id.asqi.idesa.bumdes.model.ProdukGrosir;
import id.asqi.idesa.bumdes.model.Satuan;
import id.asqi.idesa.bumdes.repository.GambarProdukGrosirRepository;
import id.asqi.idesa.bumdes.repository.KategoriProdukGrosirRepository;
import id.asqi.idesa.bumdes.repository.ProdukGrosirRepository;
import id.asqi.idesa.bumdes.repository.SatuanRepository;
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
public class ProdukGrosirService {
	private final ProdukGrosirRepository produkGrosirRepository;
	private final GambarProdukGrosirRepository gambarProdukGrosirRepository;
	private final KategoriProdukGrosirRepository kategoriProdukGrosirRepository;
	private final SatuanRepository satuanRepository;
	private final S3Storage s3Storage;

	public Page<ProdukGrosir> getAll (SearchPaginationRequest req) {
		return produkGrosirRepository.search(
				req.getSearch(),
				Auth.id(),
				req.getIsAktif(),
				req.getPagination()
		);
	}

	@Transactional
	public void create (ProdukGrosirRequest.Create req) throws Exception {
		KategoriProdukGrosir kategori = kategoriProdukGrosirRepository.findById(req.getKategoriProdukGrosirId()).orElseThrow(() -> new NotFoundEntity(KategoriProdukGrosir.class));
		Satuan satuan = satuanRepository.findById(req.getSatuanId()).orElseThrow(() -> new NotFoundEntity(Satuan.class));

		ProdukGrosir e = new ProdukGrosir();
		e.setId(Constants.idGenerator());
		e.setNama(req.getNama());
		e.setKategoriProdukGrosir(kategori);
		e.setKondisi(req.getKondisi());
		e.setSatuan(satuan);
		e.setBeratSatuanKg(req.getBeratSatuanKg());
		e.setStok(req.getStok());
		e.setHarga(req.getHarga());
		e.setHargaDiskon(req.getHargaDiskon());
		e.setIsAktif(req.getIsAktif());
		e.setDeskripsi(req.getDeskripsi());
		e.setUserBumdes(Auth.getUserBumdes());
		e.setTanggalDibuat(LocalDateTime.now());

		e = produkGrosirRepository.save(e);

//		this.uploadGambar(e, req.getGambar());
	}
	@Transactional
	public void update (ProdukGrosirRequest.Update req) throws Exception {
		ProdukGrosir e = produkGrosirRepository.findById(req.getId()).orElseThrow(() -> new NotFoundEntity(ProdukGrosir.class));
		KategoriProdukGrosir kategori = kategoriProdukGrosirRepository.findById(req.getKategoriProdukGrosirId()).orElseThrow(() -> new NotFoundEntity(KategoriProdukGrosir.class));
		Satuan satuan = satuanRepository.findById(req.getSatuanId()).orElseThrow(() -> new NotFoundEntity(Satuan.class));

		e.setNama(req.getNama());
		e.setKategoriProdukGrosir(kategori);
		e.setKondisi(req.getKondisi());
		e.setSatuan(satuan);
		e.setBeratSatuanKg(req.getBeratSatuanKg());
		e.setHarga(req.getHarga());
		e.setStok(req.getStok());
		e.setHargaDiskon(req.getHargaDiskon());
		e.setIsAktif(req.getIsAktif());
		e.setDeskripsi(req.getDeskripsi());
		e.setUserBumdes(Auth.getUserBumdes());
		e.setTanggalDibuat(LocalDateTime.now());

		e = produkGrosirRepository.save(e);

		/*figure out later*/
//		this.uploadGambar(e, req.getGambar());
	}
	public void delete (IdNumberRequest req) {
		ProdukGrosir e = produkGrosirRepository.findById(req.getId()).orElseThrow(() -> new NotFoundEntity(ProdukGrosir.class));

		validateOwner(e);

		if(! Objects.equals(e.getUserBumdes().getId(), Auth.id())) {
			throw new InvalidOperationException("Tidak dapat menghapus produkGrosir orang lain");
		}

		produkGrosirRepository.delete(e);
	}

	private void validateOwner(ProdukGrosir e){
		if(! Objects.equals(e.getUserBumdes().getId(), Auth.id())){
			throw new InvalidOperationException("ProdukGrosir ini bukan milik Anda.");
		}
	}

	private void uploadGambar (ProdukGrosir e, List<MultipartFile> files) throws Exception {
		List<GambarProdukGrosir> list = new ArrayList<>();
		for(MultipartFile file : files){
			String url = s3Storage.uploadFile(FolderName.GAMBAR_PRODUK_GROSIR, file);
			GambarProdukGrosir gambar = new GambarProdukGrosir();
			gambar.setId(Constants.idGenerator());
			gambar.setProdukGrosir(e);
			gambar.setUrl(url);
			list.add(gambar);
		}

		gambarProdukGrosirRepository.saveAll(list);
	}


}