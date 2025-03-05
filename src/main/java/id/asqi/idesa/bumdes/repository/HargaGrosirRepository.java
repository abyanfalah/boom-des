package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.HargaGrosir;
import id.asqi.idesa.bumdes.model.Jabatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HargaGrosirRepository extends JpaRepository<HargaGrosir, Long> {

	@Query("""
			 SELECT h FROM HargaGrosir h
			    WHERE 
			    (
			        (:varianId IS NULL AND h.produkMarketplaceDesa.id = :produkId)
			        OR (:varianId IS NOT NULL AND h.varianProdukMarketplaceDesa.id = :varianId)
			    )
			    AND h.kuantitasMinimum <= :jumlahPembelian
			    ORDER BY h.kuantitasMinimum DESC
			    LIMIT 1
			""")
	Optional<HargaGrosir> findByProductOrVariant(
			@Param("produkId") Long produkId,
			@Param("varianId") Long varianId
	);
}