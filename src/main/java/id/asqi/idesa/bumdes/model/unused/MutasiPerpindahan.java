package id.asqi.idesa.bumdes.model.unused;

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
@Table(name = "mutasi_perpindahan")
public class MutasiPerpindahan {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "penduduk_id")
    private Long pendudukId;

    @Column(name = "tanggal_datang")
    private LocalDate tanggalDatang;

    @Column(name = "status_penduduk_tetap")
    private Boolean statusPendudukTetap;

    @Size(max = 255)
    @Column(name = "alamat_tinggal_sekarang")
    private String alamatTinggalSekarang;

    @Size(max = 255)
    @Column(name = "dusun")
    private String dusun;

    @Size(max = 5)
    @Column(name = "rt", length = 5)
    private String rt;

    @Size(max = 5)
    @Column(name = "rw", length = 5)
    private String rw;

    @Column(name = "kewarganegaraan")
    private Integer kewarganegaraan;

    @Column(name = "golongan_darah_id")
    private Long golonganDarahId;

    @Column(name = "bisa_membaca")
    private Boolean bisaMembaca;

    @Column(name = "status_perkawinan_id")
    private Long statusPerkawinanId;

    @Size(max = 255)
    @Column(name = "nomor_akta_nikah")
    private String nomorAktaNikah;

    @Column(name = "akta_perceraian")
    private Boolean aktaPerceraian;

    @Column(name = "pendidikan_id")
    private Long pendidikanId;

    @Column(name = "pekerjaan_id")
    private Long pekerjaanId;

    @Column(name = "etnis_id")
    private Long etnisId;

    @Size(max = 100)
    @Column(name = "ayah", length = 100)
    private String ayah;

    @Size(max = 100)
    @Column(name = "ibu", length = 100)
    private String ibu;

    @Size(max = 255)
    @Column(name = "maksud_dan_tujuan")
    private String maksudDanTujuan;

    @Size(max = 255)
    @Column(name = "dibuat_oleh")
    private String dibuatOleh;

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