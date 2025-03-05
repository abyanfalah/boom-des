package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.AlamatDesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlamatDesaRepository extends JpaRepository<AlamatDesa, Long> {
	@Query("""
			SELECT e FROM AlamatDesa e
			WHERE (:q = '' OR (
				LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
							))
			""")
	Page<AlamatDesa> search(
			@Param("q") String search,
			Pageable pageable
	);
}