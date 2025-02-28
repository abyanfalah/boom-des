package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "komoditas")
public class Komoditas {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 128)
    @NotNull
    @Column(name = "nama", nullable = false, length = 128)
    private String nama;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "kategori_komoditas_id", nullable = false)
    private
    KategoriKomoditas kategoriKomoditas;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private LocalDateTime tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}