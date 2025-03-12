package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.KategoriDasarTagihanDesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KategoriDasarTagihanDesaRepository extends JpaRepository<KategoriDasarTagihanDesa, Long> {

	String searchQuery = """
            SELECT e FROM KategoriDasarTagihanDesa e
            WHERE (:q = '' OR (
                LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
            ))
            AND (:isAktif IS NULL OR e.isAktif = :isAktif)
            AND (:isIncludeDeleted = TRUE  OR e.tanggalDihapus IS NULL)
            """;

	@Query(searchQuery)
	List<KategoriDasarTagihanDesa> search(
			@Param("q") String search,
			@Param("isAktif") Boolean isAktif,
			@Param("isIncludeDeleted") Boolean isIncludeDeleted
	);

	@Query(searchQuery)
	Page<KategoriDasarTagihanDesa> search(
			@Param("q") String search,
			@Param("isAktif") Boolean isAktif,
			@Param("isIncludeDeleted") Boolean isIncludeDeleted,
			Pageable pageable
	);
}