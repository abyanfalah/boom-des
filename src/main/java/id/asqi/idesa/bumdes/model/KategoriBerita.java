package id.asqi.idesa.bumdes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "kategori_berita")
public class KategoriBerita {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 64)
    @NotNull
    @Column(name = "nama", nullable = false, length = 64)
    private String nama;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private Instant tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}