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
@Table(name = "aspirasi")
public class Aspirasi {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tanggal")
    private LocalDate tanggal;

    @Size(max = 100)
    @Column(name = "nama_penyampai", length = 100)
    private String namaPenyampai;

    @Column(name = "bidang_id")
    private Long bidangId;

    @Size(max = 255)
    @Column(name = "hasil_kajian")
    private String hasilKajian;

    @Size(max = 255)
    @Column(name = "tindak_lanjut")
    private String tindakLanjut;

    @Column(name = "status_pengiriman")
    private Integer statusPengiriman;

    @Size(max = 255)
    @Column(name = "aspirasi_yg_disampaikan")
    private String aspirasiYgDisampaikan;

    @Column(name = "tanggal_disalurkan")
    private LocalDate tanggalDisalurkan;

    @Column(name = "status")
    private Integer status;

    @Size(max = 255)
    @Column(name = "isi")
    private String isi;

    @Column(name = "tanggapan")
    private Boolean tanggapan;

    @Size(max = 255)
    @Column(name = "foto")
    private String foto;

    @Column(name = "status_aspirasi")
    private Short statusAspirasi;

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