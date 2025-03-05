package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.unused.AlamatProvinsi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlamatProvinsiRepository extends JpaRepository<AlamatProvinsi, Long> {
	@Query("""
			SELECT e FROM AlamatProvinsi e
			WHERE (:q = '' OR (
				LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
							))
			""")
	Page<AlamatProvinsi> search(
			@Param("q") String search,
			Pageable pageable
	);
}