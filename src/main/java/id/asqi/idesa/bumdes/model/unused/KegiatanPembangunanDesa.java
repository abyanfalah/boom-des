package id.asqi.idesa.bumdes.model.unused;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "kegiatan_pembangunan_desa")
public class KegiatanPembangunanDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rencana_id")
    private RencanaPembangunanDesa rencana;

    @Column(name = "sifat_proyek")
    private Integer sifatProyek;

    @Column(name = "pegawai_desa_id")
    private Long pegawaiDesaId;

    @Column(name = "jumlah_realisasi_biaya")
    private Long jumlahRealisasiBiaya;

    @Size(max = 255)
    @Column(name = "waktu_pelaksanaan")
    private String waktuPelaksanaan;

    @Size(max = 255)
    @Column(name = "volume")
    private String volume;

    @Size(max = 50)
    @Column(name = "satuan", length = 50)
    private String satuan;

    @Size(max = 255)
    @Column(name = "foto_mulai")
    private String fotoMulai;

    @Size(max = 255)
    @Column(name = "foto_menengah")
    private String fotoMenengah;

    @Size(max = 255)
    @Column(name = "foto_selesai")
    private String fotoSelesai;

    @Size(max = 255)
    @Column(name = "koordinat")
    private String koordinat;

    @Column(name = "tanggal_ditambah")
    private Instant tanggalDitambah;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}