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
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pengaduan_masyarakat")
public class PengaduanMasyarakat {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "penduduk_id")
    private Long pendudukId;

    @Column(name = "tanggal")
    private LocalDate tanggal;

    @Size(max = 255)
    @Column(name = "deskripsi")
    private String deskripsi;

    @Size(max = 255)
    @Column(name = "tindak_lanjut")
    private String tindakLanjut;

    @Size(max = 255)
    @Column(name = "catatan")
    private String catatan;

    @Size(max = 255)
    @Column(name = "isi_aspirasi")
    private String isiAspirasi;

    @Column(name = "status")
    private Integer status;

    @Size(max = 255)
    @Column(name = "tanggapan")
    private String tanggapan;

    @NotNull
    @Column(name = "alamat_desa_id", nullable = false)
    private Long alamatDesaId;

    @NotNull
    @Column(name = "alamat_kecamatan_id", nullable = false)
    private Long alamatKecamatanId;

    @NotNull
    @Column(name = "alamat_kabupaten_id", nullable = false)
    private Long alamatKabupatenId;

    @NotNull
    @Column(name = "alamat_provinsi_id", nullable = false)
    private Long alamatProvinsiId;

    @Column(name = "tanggal_ditambah")
    private Instant tanggalDitambah;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}