package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.GambarInformasi;
import id.asqi.idesa.bumdes.model.Informasi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GambarInformasiRepository extends JpaRepository<GambarInformasi, Long> {

}