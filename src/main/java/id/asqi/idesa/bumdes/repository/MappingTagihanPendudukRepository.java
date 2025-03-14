package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.MappingTagihanPenduduk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MappingTagihanPendudukRepository extends JpaRepository<MappingTagihanPenduduk, Long> {
	String searchQuery = """
            SELECT e FROM MappingTagihanPenduduk e
			WHERE (:q = '' OR (
				LOWER(e.penduduk.nama) LIKE LOWER(CONCAT('%', :q, '%'))
				OR LOWER(e.penduduk.nik) LIKE LOWER(CONCAT('%', :q, '%'))
				))
			AND (:isIncludeDeleted = TRUE OR e.tanggalDihapus IS NULL)
            AND (:alamatDesaId IS NULL OR e.alamatDesa.id = :alamatDesaId)
            AND (:userBumdesId IS NULL OR e.userBumdes.id = :userBumdesId)
			""";

	@Query(searchQuery)
	Page<MappingTagihanPenduduk> search(
			@Param("q") String search,
			@Param("isIncludeDeleted") Boolean isIncludeDeleted,
			@Param("alamatDesaId") Long alamatDesaId,
			@Param("userBumdesId") Long userBumdesId,
			Pageable pageable
	);

}