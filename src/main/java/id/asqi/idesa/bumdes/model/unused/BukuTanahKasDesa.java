package id.asqi.idesa.bumdes.model.unused;

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
@Table(name = "buku_tanah_kas_desa")
public class BukuTanahKasDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "nomor")
    private String nomor;

    @Column(name = "luas_tanah")
    private Integer luasTanah;

    @Column(name = "kelas")
    private Integer kelas;

    @Column(name = "persil")
    private Integer persil;

    @Column(name = "letter_c")
    private Integer letterC;

    @Size(max = 255)
    @Column(name = "asal_perolehan")
    private String asalPerolehan;

    @Column(name = "tanggal_perolehan")
    private LocalDate tanggalPerolehan;

    @Column(name = "jenis", length = Integer.MAX_VALUE)
    private String jenis;

    @Column(name = "patok_tanah")
    private Boolean patokTanah;

    @Column(name = "papan_nama")
    private Boolean papanNama;

    @Size(max = 255)
    @Column(name = "peruntukan")
    private String peruntukan;

    @Size(max = 255)
    @Column(name = "mutasi")
    private String mutasi;

    @Size(max = 255)
    @Column(name = "koordinat")
    private String koordinat;

    @Size(max = 255)
    @Column(name = "batas_utara")
    private String batasUtara;

    @Size(max = 255)
    @Column(name = "batas_timur")
    private String batasTimur;

    @Size(max = 255)
    @Column(name = "batas_selatan")
    private String batasSelatan;

    @Size(max = 255)
    @Column(name = "batas_barat")
    private String batasBarat;

    @Size(max = 255)
    @Column(name = "berkas")
    private String berkas;

    @Size(max = 255)
    @Column(name = "keterangan")
    private String keterangan;

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