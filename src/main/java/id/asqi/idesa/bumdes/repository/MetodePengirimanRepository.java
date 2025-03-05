package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.MetodePengiriman;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetodePengirimanRepository extends JpaRepository<MetodePengiriman, Long> {
	@Query("""
			SELECT e FROM MetodePengiriman e
			WHERE e.id IN :metodePengirimanIdList
			""")
	List<MetodePengiriman> findByIdList(
			@Param("metodePengirimanIdList") List<Long> metodePengirimanIdList
	);
}