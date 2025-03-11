package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.KategoriKomoditas;
import id.asqi.idesa.bumdes.model.KategoriPostinganForumBumdes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KategoriPostinganForumBumdesRepository extends JpaRepository<KategoriPostinganForumBumdes, Long> {


	String searchQuery = """
			SELECT e FROM KategoriPostinganForumBumdes e
			WHERE (:q = '' OR (
				LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
							))
			AND (:isIncludeDeleted = TRUE OR e.tanggalDihapus IS NULL )
			""";

	@Query(searchQuery)
	Page<KategoriPostinganForumBumdes> search(
			@Param("q") String search,
			@Param("isIncludeDeleted") Boolean isIncludeDeleted,
			Pageable pageable
	);

	@Query(searchQuery)
	List<KategoriPostinganForumBumdes> search(
			@Param("q") String search,
			@Param("isIncludeDeleted") Boolean isIncludeDeleted

	);

}