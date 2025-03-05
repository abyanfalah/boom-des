package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.JenisMitra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JenisMitraRepository extends JpaRepository<JenisMitra, Long> {
	@Query("""
			SELECT e FROM JenisMitra e
			WHERE (:q = '' OR (
				LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
				OR LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
							))
			""")
	Page<JenisMitra> search(
			@Param("q") String search,
			Pageable pageable
	);
}