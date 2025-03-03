package id.asqi.idesa.bumdes.model;

import id.asqi.idesa.bumdes.model.unused.Berita;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "dokumen_berita")
public class DokumenBerita {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "url", nullable = false, length = Integer.MAX_VALUE)
    private String url;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "berita_id", nullable = false)
    private Berita berita;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private Instant tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}