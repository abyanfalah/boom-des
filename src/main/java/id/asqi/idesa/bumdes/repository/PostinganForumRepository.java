package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.PostinganForum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostinganForumRepository extends JpaRepository<PostinganForum, Long> {

	String searchQuery = """
            SELECT p FROM PostinganForum p
            WHERE (:q = '' OR (
                LOWER(p.judul) LIKE LOWER(CONCAT('%', :q, '%'))
            ))
            AND (:isIncludeDeleted = TRUE OR p.tanggalDihapus IS NULL)
            AND (:alamatDesaId IS NULL OR p.alamatDesa.id = :alamatDesaId)
            AND (:userBumdesId IS NULL OR p.userBumdes.id = :userBumdesId)
            """;

	@Query(searchQuery)
	Page<PostinganForum> search(
			@Param("q") String search,
			@Param("isIncludeDeleted") Boolean isIncludeDeleted,
			@Param("alamatDesaId") Long alamatDesaId,
			@Param("userBumdesId") Long userBumdesId,
			Pageable pageable
	);

	@Query(searchQuery)
	List<PostinganForum> search(
			@Param("q") String search,
			@Param("isIncludeDeleted") Boolean isIncludeDeleted,
			@Param("alamatDesaId") Long alamatDesaId,
			@Param("userBumdesId") Long userBumdesId
	);
}