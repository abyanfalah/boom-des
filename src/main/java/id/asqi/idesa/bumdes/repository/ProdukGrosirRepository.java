package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.ProdukGrosir;
import id.asqi.idesa.bumdes.model.ProdukGrosir;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdukGrosirRepository extends JpaRepository<ProdukGrosir, Long> {
	@Query("""
			SELECT e FROM ProdukGrosir e
			WHERE (:q = '' OR (
				LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
			))
			AND e.userBumdes.id = :userBumdesId
			AND e.isAktif = :isAktif
			""")
	Page<ProdukGrosir> search (
			@Param("q") String search,
			@Param("userBumdesId") Long userBumdesId,
			@Param("isAktif") Boolean isAktif,
			Pageable pageable
	);

	@Query("""
			SELECT e FROM ProdukGrosir e
			WHERE (:q = '' OR (
				LOWER(e.nama) LIKE LOWER(CONCAT('%', :q, '%'))
				AND e.isAktif = :isAktif
			))
			""")
	Page<ProdukGrosir> search (
			@Param("q") String search,
			@Param("isAktif") Boolean isAktif,
			Pageable pageable
	);
}