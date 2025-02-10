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
@Table(name = "pelaksanaan_pilkades")
public class PelaksanaanPilkades{
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tanggal")
    private LocalDate tanggal;

    @Size(max = 100)
    @Column(name = "nama_calon", length = 100)
    private String namaCalon;

    @Column(name = "nomor_urut")
    private Integer nomorUrut;

    @Column(name = "perolehan_suara")
    private Integer perolehanSuara;

    @Size(max = 255)
    @Column(name = "tindak_lanjut")
    private String tindakLanjut;

    @Size(max = 100)
    @Column(name = "dibuat_oleh", length = 100)
    private String dibuatOleh;

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

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}