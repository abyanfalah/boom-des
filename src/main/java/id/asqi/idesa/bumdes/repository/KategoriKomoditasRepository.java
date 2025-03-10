package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.KategoriKomoditas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KategoriKomoditasRepository extends JpaRepository<KategoriKomoditas, Long> {

	String searchQuery = """
			SELECT e FROM KategoriKomoditas e
			WHERE (:q = '' OR (
				LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
							))
			AND (:isIncludeDeleted = TRUE OR e.tanggalDihapus IS NULL )
			""";

	@Query(searchQuery)
	Page<KategoriKomoditas> search(
			@Param("q") String search,
			@Param("isIncludeDeleted") Boolean isIncludeDeleted,
			Pageable pageable
	);

	@Query(searchQuery)
	List<KategoriKomoditas> search(
			@Param("q") String search,
			@Param("isIncludeDeleted") Boolean isIncludeDeleted

	);

}