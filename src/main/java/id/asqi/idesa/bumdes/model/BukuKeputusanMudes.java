package id.asqi.idesa.bumdes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "buku_keputusan_mudes")
public class BukuKeputusanMudes {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tanggal_kegiatan")
    private LocalDate tanggalKegiatan;

    @Size(max = 255)
    @Column(name = "dasar_pelaksanaan")
    private String dasarPelaksanaan;

    @Size(max = 255)
    @Column(name = "tentang")
    private String tentang;

    @Column(name = "jumlah_peserta")
    private Integer jumlahPeserta;

    @Size(max = 255)
    @Column(name = "pimpinan_mudes")
    private String pimpinanMudes;

    @Size(max = 255)
    @Column(name = "notulen")
    private String notulen;

    @Size(max = 255)
    @Column(name = "narasumber")
    private String narasumber;

    @Size(max = 255)
    @Column(name = "pokok_keputusan")
    private String pokokKeputusan;

    @Size(max = 255)
    @Column(name = "keterangan")
    private String keterangan;

    @Size(max = 255)
    @Column(name = "foto")
    private String foto;

    @Column(name = "alamat_desa_id")
    private Long alamatDesaId;

    @Column(name = "alamat_kecamatan_id")
    private Long alamatKecamatanId;

    @Column(name = "alamat_kabupaten_id")
    private Long alamatKabupatenId;

    @Column(name = "alamat_provinsi_id")
    private Long alamatProvinsiId;

    @Column(name = "tanggal_ditambah")
    private Instant tanggalDitambah;

}