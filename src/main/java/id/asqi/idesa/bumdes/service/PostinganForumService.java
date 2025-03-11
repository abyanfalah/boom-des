package id.asqi.idesa.bumdes.service;


import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.core.service.S3Storage;
import id.asqi.idesa.bumdes.enums.FolderName;
import id.asqi.idesa.bumdes.http.request.PostinganForumRequest;
import id.asqi.idesa.bumdes.model.GambarPostinganForum;
import id.asqi.idesa.bumdes.model.GambarProdukGrosir;
import id.asqi.idesa.bumdes.model.KategoriPostinganForumBumdes;
import id.asqi.idesa.bumdes.model.PostinganForum;
import id.asqi.idesa.bumdes.repository.GambarPostinganForumRepository;
import id.asqi.idesa.bumdes.repository.KategoriPostinganForumBumdesRepository;
import id.asqi.idesa.bumdes.repository.PostinganForumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostinganForumService {
	private final PostinganForumRepository postinganForumRepository;
	private final KategoriPostinganForumBumdesRepository kategoriRepository;
	private final GambarPostinganForumRepository gambarPostinganForumRepository;
	private final S3Storage s3Storage;

	public Page<PostinganForum> getAll(SearchPaginationRequest req) {
		return postinganForumRepository.search(req.getSearch(), req.getIsIncludeDeleted(), req.getPagination());
	}

	public void create(PostinganForumRequest.Create req) throws Exception {
		KategoriPostinganForumBumdes kategori = this.findKategoriById(req.getKategoriId());

		PostinganForum e = new PostinganForum();
		e.setId(Constants.idGenerator());
		e.setJudul(req.getJudul());
		e.setIsi(req.getIsi());
		e.setKategori(kategori);
		e.setTanggalDibuat(LocalDateTime.now());
		e = postinganForumRepository.save(e);

//		this.handleGambar(e,req.getGambar());
	}

	public void update(PostinganForumRequest.Update req) {
		PostinganForum e = findById(req.getId());
		KategoriPostinganForumBumdes kategori = this.findKategoriById(req.getKategoriId());

		e.setJudul(req.getJudul());
		e.setIsi(req.getIsi());
		e.setKategori(kategori);
		e.setTanggalDiubah(LocalDateTime.now());
		postinganForumRepository.save(e);

//		this.handleGambar(e,req.getGambar());
	}

	public void softDelete(SetDeleteStatusRequest req) {
		PostinganForum e = findById(req.getId());
		e.setTanggalDihapus(req.getIsDeleted() ? LocalDateTime.now() : null);
		postinganForumRepository.save(e);
	}

	private PostinganForum findById(Long id) {
		return postinganForumRepository.findById(id).orElseThrow(() -> new NotFoundEntity(PostinganForum.class));
	}

	private KategoriPostinganForumBumdes findKategoriById(Long id) {
		return kategoriRepository.findById(id).orElseThrow(() -> new NotFoundEntity(KategoriPostinganForumBumdes.class));
	}

	private void handleGambar (PostinganForum e, List<MultipartFile> files) throws Exception {
		List<GambarPostinganForum> list = new ArrayList<>();
		for(MultipartFile file : files){
			String url = s3Storage.uploadFile(FolderName.GAMBAR_POSTINGAN_FORUM, file);
			GambarPostinganForum gambar = new GambarPostinganForum();
			gambar.setId(Constants.idGenerator());
			gambar.setPostinganForum(e);
			gambar.setUrl(url);
			list.add(gambar);
		}

		gambarPostinganForumRepository.saveAll(list);
	}
}