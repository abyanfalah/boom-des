package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.KategoriTagihanDesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KategoriTagihanDesaRepository extends JpaRepository<KategoriTagihanDesa, Long> {
	String searchQuery = """
			 	    SELECT ktd FROM KategoriTagihanDesa ktd
			WHERE (:alamatDesaId IS NULL OR ktd.alamatDesa.id = :alamatDesaId)
			AND (:q = '' OR
				LOWER(ktd.nama) LIKE LOWER(concat('%', :q, '%'))
			)
	        AND (:isAktif IS NULL OR ktd.isAktif = :isAktif)
		    AND (:isIncludeDeleted = TRUE  OR ktd.tanggalDihapus IS NULL)
	""";

	@Query(searchQuery)
	Page<KategoriTagihanDesa> search (
			@Param("q") String search,
			@Param("isAktif") Boolean isAktif,
			@Param("isIncludeDeleted") Boolean isIncludeDeleted,
			@Param("alamatDesaId") Long alamatDesaId,
			Pageable pageable
	);

	@Query(searchQuery)
	List<KategoriTagihanDesa> search (
			@Param("q") String search,
			@Param("isAktif") Boolean isAktif,
			@Param("isIncludeDeleted") Boolean isIncludeDeleted,
			@Param("alamatDesaId") Long alamatDesaId
	);
}