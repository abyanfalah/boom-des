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
@Table(name = "peraturan_kades")
public class PeraturanKades {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "tentang")
    private String tentang;

    @Size(max = 255)
    @Column(name = "nomor_peraturan")
    private String nomorPeraturan;

    @Size(max = 10)
    @Column(name = "tahun", length = 10)
    private String tahun;

    @Column(name = "tanggal_ditetapkan")
    private LocalDate tanggalDitetapkan;

    @Size(max = 20)
    @Column(name = "nomor_pendaftaran_camat_bupati", length = 20)
    private String nomorPendaftaranCamatBupati;

    @Column(name = "tanggal_dilaporkan")
    private LocalDate tanggalDilaporkan;

    @Size(max = 20)
    @Column(name = "nomor_berita_desa", length = 20)
    private String nomorBeritaDesa;

    @Column(name = "tanggal_diundangkan")
    private LocalDate tanggalDiundangkan;

    @Size(max = 255)
    @Column(name = "uraian")
    private String uraian;

    @Size(max = 255)
    @Column(name = "berkas")
    private String berkas;

    @Size(max = 255)
    @Column(name = "dibuat_oleh")
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

    @Column(name = "tanggal_diuabah")
    private Instant tanggalDiuabah;

    @Size(max = 4)
    @Column(name = "periode", length = 4)
    private String periode;

    @Size(max = 4)
    @Column(name = "tahun_diundangkan", length = 4)
    private String tahunDiundangkan;

}