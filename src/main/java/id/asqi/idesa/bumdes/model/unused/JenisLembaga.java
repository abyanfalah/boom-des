package id.asqi.idesa.bumdes.model.unused;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "jenis_lembaga")
public class JenisLembaga {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 200)
    @Column(name = "nama", length = 200)
    private String nama;

    @Column(name = "tanggal_ditambah")
    private Instant tanggalDitambah;

}