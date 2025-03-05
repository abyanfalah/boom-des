package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.Penduduk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PendudukRepository extends JpaRepository<Penduduk, Long> {
	@Query("""
			SELECT e FROM Penduduk e
			WHERE (:q = '' OR (
				LOWER(e.nik) LIKE LOWER(CONCAT('%', :q, '%'))
				OR LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
							))
			""")
	Page<Penduduk> search(
			@Param("q") String search,
			Pageable pageable
	);
}