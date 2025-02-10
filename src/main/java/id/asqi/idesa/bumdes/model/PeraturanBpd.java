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
@Table(name = "peraturan_bpd")
public class PeraturanBpd {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tanggal_penetapan")
    private LocalDate tanggalPenetapan;

    @Size(max = 5)
    @Column(name = "tahun", length = 5)
    private String tahun;

    @Size(max = 255)
    @Column(name = "nomor_peraturan")
    private String nomorPeraturan;

    @Column(name = "tipe_keputusan_id")
    private Long tipeKeputusanId;

    @Size(max = 255)
    @Column(name = "tentang")
    private String tentang;

    @Size(max = 255)
    @Column(name = "uraian")
    private String uraian;

    @Size(max = 255)
    @Column(name = "keterangan")
    private String keterangan;

    @Size(max = 255)
    @Column(name = "berkas")
    private String berkas;

    @Column(name = "alamat_desa_id")
    private Long alamatDesaId;

    @Column(name = "alamat_kecamatan_id")
    private Long alamatKecamatanId;

    @Column(name = "alamat_kabupaten_id")
    private Long alamatKabupatenId;

    @Column(name = "alamat_provinsi_id")
    private Long alamatProvinsiId;

    @Size(max = 4)
    @Column(name = "periode", length = 4)
    private String periode;

    @Column(name = "tanggal_ditambah")
    private Instant tanggalDitambah;

}