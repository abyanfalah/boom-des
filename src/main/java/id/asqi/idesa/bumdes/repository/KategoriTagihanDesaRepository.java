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
		WHERE ktd.alamatDesa.id = :alamatDesaId
		AND (:q = '' OR 
			LOWER(ktd.nama) LIKE LOWER(concat('%', :q, '%'))
		)
			""";

	@Query(searchQuery)
	Page<KategoriTagihanDesa> findByAlamatDesaId(
			@Param("q") String q,
			@Param("alamatDesaId") Long alamatDesaId,
			Pageable pageable
	);

	@Query(searchQuery)
	List<KategoriTagihanDesa> listByAlamatDesaId(
			@Param("q") String q,
			@Param("alamatDesaId") Long alamatDesaId,
			Pageable pageable
	);
}