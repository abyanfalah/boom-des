package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.Mitra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MitraRepository extends JpaRepository<Mitra, Long> {
	@Query("""
			SELECT e FROM Mitra e
			WHERE (:q = '' OR (
				LOWER(e.penduduk.nama) LIKE LOWER(CONCAT('%', :q, '%'))
				OR LOWER(e.penduduk.nik) LIKE LOWER(CONCAT('%', :q, '%'))
				))
			""")
	Page<Mitra> search(
			@Param("q") String search,
			Pageable pageable
	);
}