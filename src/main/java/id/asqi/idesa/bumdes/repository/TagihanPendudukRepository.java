package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.TagihanPenduduk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagihanPendudukRepository extends JpaRepository<TagihanPenduduk, Long> {

	String searchQuery = """
			SELECT tp FROM TagihanPenduduk tp
			WHERE (:q = '' OR
				LOWER(tp.penduduk.nama) LIKE LOWER(concat('%', :q, '%'))
			)
			AND (:alamatDesaId IS NULL OR tp.penduduk.alamatDesa.id = :alamatDesaId)
			AND (:pendudukId IS NULL OR tp.penduduk.id = :pendudukId)
			AND (:kategoriTagihanId IS NULL OR tp.kategoriTagihanDesa.id = :kategoriTagihanId)
			
			""";

	@Query(searchQuery)
	Page<TagihanPenduduk> search (
			@Param("q") String search,
			@Param("alamatDesaId") Long alamatDesaId,
			@Param("pendudukId") Long pendudukId,
			@Param("kategoriTagihanId") Long kategoriTagihanId,
			Pageable pageable
	);
}