package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.KomentarPostinganForumBumdes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface KomentarPostinganForumBumdesRepository extends JpaRepository<KomentarPostinganForumBumdes, Long> {

	@Query("""
            SELECT k FROM KomentarPostinganForumBumdes k
            WHERE (:q = '' OR
                LOWER(k.isi) LIKE LOWER(CONCAT('%', :q, '%'))
            )
            AND k.postinganForum.id = :postinganForumId
            AND (:parentKomentarId IS NULL OR  k.parentKomentar.id = :parentKomentarId)
            """)
	Page<KomentarPostinganForumBumdes> search(
			@Param("q") String search,
			@Param("postinganForumId") Long postinganForumId,
			@Param("parentKomentarId") Long parentKomentarId,
			Pageable pageable
	);

	@Query("""
            SELECT k FROM KomentarPostinganForumBumdes k
            WHERE k.id = :id
            AND k.parentKomentar IS NULL
            """)
	Optional<KomentarPostinganForumBumdes> findParentById(
			@Param("id") Long id
	);

}