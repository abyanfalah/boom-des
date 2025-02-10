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
@Table(name = "buku_tanah_didesa")
public class BukuTanahDidesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "status_penduduk")
    private Integer statusPenduduk;

    @Column(name = "penduduk_id")
    private Long pendudukId;

    @Size(max = 100)
    @Column(name = "nama_pemilik", length = 100)
    private String namaPemilik;

    @Size(max = 255)
    @Column(name = "alamat")
    private String alamat;

    @Size(max = 255)
    @Column(name = "lokasi_bidang_tanah")
    private String lokasiBidangTanah;

    @Size(max = 255)
    @Column(name = "nomor")
    private String nomor;

    @Column(name = "jenis_alas_hak_id")
    private Long jenisAlasHakId;

    @Column(name = "penggunaan_tanah_id")
    private Long penggunaanTanahId;

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

    @Column(name = "luas")
    private Integer luas;

    @Column(name = "tanggal_mutasi")
    private LocalDate tanggalMutasi;

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