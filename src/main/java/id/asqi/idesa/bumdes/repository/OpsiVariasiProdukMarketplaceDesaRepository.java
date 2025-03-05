package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.OpsiVariasiProdukMarketplaceDesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OpsiVariasiProdukMarketplaceDesaRepository extends JpaRepository<OpsiVariasiProdukMarketplaceDesa, Long> {
	@Query("""
			SELECT e FROM OpsiVariasiProdukMarketplaceDesa e
			WHERE (:q = '' OR (
				LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
							))
			""")
	Page<OpsiVariasiProdukMarketplaceDesa> search(
			@Param("q") String search,
			Pageable pageable
	);
}