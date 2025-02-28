package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.RekeningUserBumdes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RekeningUserBumdesRepository extends JpaRepository<RekeningUserBumdes, Long> {

	@Query("""
			SELECT e FROM RekeningUserBumdes e
			WHERE (:q = '' OR (
				LOWER(e.atasNama) LIKE LOWER(CONCAT('%', :q, '%'))
				OR LOWER(e.namaBank) LIKE LOWER(CONCAT('%', :q, '%'))
				OR LOWER(e.nomorRekening) LIKE LOWER(CONCAT('%', :q, '%'))
			))
			AND e.userBumdes.id = :userBumdesId
			""")
	Page<RekeningUserBumdes> search(
			@Param("q") String search,
			@Param("userBumdesId") Long userBumdesId,
			Pageable pageable
	);

	@Query("SELECT COUNT(rub) < 3 FROM RekeningUserBumdes rub WHERE rub.userBumdes.id = :userBumdesId")
	Boolean hasMaxThreeRekening(
			@Param("userBumdesId") Long userBumdesId
	);

	@Query("UPDATE RekeningUserBumdes rub SET rub.isRekeningUtama = FALSE WHERE rub.userBumdes.id = :userBumdesId")
	void resetRekeningUtama (@Param("userBumdesId") Long userBumdesId);
}