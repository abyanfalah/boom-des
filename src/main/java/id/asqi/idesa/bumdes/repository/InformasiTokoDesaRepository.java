package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.InformasiTokoDesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InformasiTokoDesaRepository extends JpaRepository<InformasiTokoDesa, Long> {
	@Query("""
			SELECT e FROM InformasiTokoDesa e
			WHERE e.alamatDesa.id = :alamatDesaId
			""")
	Optional<InformasiTokoDesa> findByAlamatDesaId (
			@Param("alamatDesaId") Long alamatDesaId
	);

	@Query("""
			SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END
			FROM InformasiTokoDesa e
			WHERE e.alamatDesa.id = :alamatDesaId
			""")
	Boolean hasInfoTokoByDesaId(
			@Param("alamatDesaId") Long alamatDesaId
	);

}