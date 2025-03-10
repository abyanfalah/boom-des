package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.dto.ProdukMarketplaceDesaDTO;
import id.asqi.idesa.bumdes.model.ProdukMarketplaceDesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdukMarketplaceDesaRepository extends JpaRepository<ProdukMarketplaceDesa, Long> {

	String searchQuery = """
			SELECT e FROM ProdukMarketplaceDesa e
			WHERE (:q = '' OR (
				LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
							))
			AND e.alamatDesa.id = :alamatDesaId
			""";

	@Query(searchQuery)
	Page<ProdukMarketplaceDesa> search(
			@Param("q") String search,
			@Param("alamatDesaId") Long alamatDesaId,
			Pageable pageable
	);

	@Query(searchQuery)
	Page<ProdukMarketplaceDesaDTO> searchDTO(
			@Param("q") String search,
			@Param("alamatDesaId") Long alamatDesaId,
			Pageable pageable
	);
}