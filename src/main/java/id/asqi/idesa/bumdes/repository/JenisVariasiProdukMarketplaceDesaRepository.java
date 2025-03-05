package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.JenisVariasiProdukMarketplaceDesa;
import id.asqi.idesa.bumdes.model.ProdukMarketplaceDesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JenisVariasiProdukMarketplaceDesaRepository extends JpaRepository<JenisVariasiProdukMarketplaceDesa, Long> {
	@Query("""
			SELECT e FROM JenisVariasiProdukMarketplaceDesa e
			WHERE (:q = '' OR (
				LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
							))
			""")
	Page<JenisVariasiProdukMarketplaceDesa> search(
			@Param("q") String search,
			Pageable pageable
	);
}