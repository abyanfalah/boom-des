package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.Komoditas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KomoditasRepository extends JpaRepository<Komoditas, Long> {

	String searchQuery = """
			SELECT e FROM Komoditas e
			WHERE (:q = '' OR (
				LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
							))
			AND (:isIncludeDeleted = TRUE OR e.tanggalDihapus IS NULL )
			""";


	@Query(searchQuery)
	Page<Komoditas> search(
			@Param("q") String search,
			@Param("isIncludeDeleted") Boolean isIncludeDeleted,
			Pageable pageable
	);
}