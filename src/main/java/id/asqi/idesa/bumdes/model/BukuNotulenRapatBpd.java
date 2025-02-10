package id.asqi.idesa.bumdes.model;

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
@Table(name = "buku_notulen_rapat_bpd")
public class BukuNotulenRapatBpd {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "materi")
    private String materi;

    @Size(max = 255)
    @Column(name = "ringkasan_hasil")
    private String ringkasanHasil;

    @Column(name = "jumlah_peserta")
    private Integer jumlahPeserta;

    @Size(max = 255)
    @Column(name = "unsur_peserta")
    private String unsurPeserta;

    @Size(max = 255)
    @Column(name = "keterangan")
    private String keterangan;

    @Size(max = 255)
    @Column(name = "foto_kegiatan")
    private String fotoKegiatan;

    @Size(max = 255)
    @Column(name = "foto_ba")
    private String fotoBa;

    @Size(max = 255)
    @Column(name = "foto_notulen")
    private String fotoNotulen;

    @Size(max = 255)
    @Column(name = "foto_daftar_hadir")
    private String fotoDaftarHadir;

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