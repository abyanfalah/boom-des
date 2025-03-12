package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.http.request.KomentarPostinganForumBumdesRequest;
import id.asqi.idesa.bumdes.model.KomentarPostinganForumBumdes;
import id.asqi.idesa.bumdes.model.PostinganForum;
import id.asqi.idesa.bumdes.model.UserBumdes;
import id.asqi.idesa.bumdes.repository.KomentarPostinganForumBumdesRepository;
import id.asqi.idesa.bumdes.repository.PostinganForumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KomentarPostinganForumBumdesService {
	private final KomentarPostinganForumBumdesRepository komentarPostinganForumBumdesRepository;
	private final PostinganForumRepository postinganForumRepository;

	public Page<KomentarPostinganForumBumdes> getAll(KomentarPostinganForumBumdesRequest.Filter req) {
		return komentarPostinganForumBumdesRepository.search(
				req.getSearch(),
				req.getPostinganForumId(),
				req.getParentKomentarId(),
				req.getPagination()
		);
	}

	public void create(KomentarPostinganForumBumdesRequest.Create req) {
		PostinganForum postingan = this.findPostinganById(req.getPostinganForumId());

		KomentarPostinganForumBumdes e = new KomentarPostinganForumBumdes();
		e.setId(Constants.idGenerator());
		e.setIsi(req.getIsi());
		e.setUserBumdes(Auth.getUserBumdes());
		e.setPostinganForum(postingan);

		if(req.getParentKomentarId() != null){
			KomentarPostinganForumBumdes parentKomentar = this.findParentKomentarById(req.getParentKomentarId());
			e.setParentKomentar(parentKomentar);
		}

		e.setTanggalDibuat(LocalDateTime.now());
		komentarPostinganForumBumdesRepository.save(e);
	}

	public void update(KomentarPostinganForumBumdesRequest.Update req) {
		KomentarPostinganForumBumdes komentar = findById(req.getId());
		komentar.setIsi(req.getIsi());
		komentar.setTanggalDiubah(LocalDateTime.now());
		komentarPostinganForumBumdesRepository.save(komentar);
	}

	public void delete(SetDeleteStatusRequest req) {
		komentarPostinganForumBumdesRepository.deleteById(req.getId());
	}

	private KomentarPostinganForumBumdes findById(Long id) {
		return komentarPostinganForumBumdesRepository.findById(id).orElseThrow(() -> new NotFoundEntity(KomentarPostinganForumBumdes.class));
	}

	private KomentarPostinganForumBumdes findParentKomentarById(Long id) {
		return komentarPostinganForumBumdesRepository.findParentById(id).orElseThrow(() -> new NotFoundEntity("parent komentar"));
	}

	private PostinganForum findPostinganById (Long id){
		return postinganForumRepository.findById(id).orElseThrow(()-> new NotFoundEntity(PostinganForum.class));
	}
}