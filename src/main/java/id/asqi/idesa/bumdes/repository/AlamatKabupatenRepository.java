package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.AlamatKabupaten;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlamatKabupatenRepository extends JpaRepository<AlamatKabupaten, Long> {
	@Query("""
			SELECT e FROM AlamatKabupaten e
			WHERE (:q = '' OR (
				LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
							))
			""")
	Page<AlamatKabupaten> search(
			@Param("q") String search,
			Pageable pageable
	);
}