package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.BeritaBumdes;
import id.asqi.idesa.bumdes.model.Informasi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BeritaBumdesRepository extends JpaRepository<BeritaBumdes, Long> {
	@Query("""
			SELECT e FROM BeritaBumdes e
			WHERE (:q = '' OR (
				LOWER(e.judul) LIKE LOWER(CONCAT('%', :q, '%'))
				OR LOWER(e.isi) LIKE LOWER(CONCAT('%', :q, '%'))
							))
			AND e.userBumdes.id = :userBumdesId
			""")
	Page<BeritaBumdes> search(
			@Param("q") String search,
			@Param("userBumdesId") Long userBumdesId,
			Pageable pageable
	);
}