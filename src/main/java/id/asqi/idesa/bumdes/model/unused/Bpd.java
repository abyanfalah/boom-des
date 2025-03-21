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
@Table(name = "bpd")
public class Bpd {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "agama_id")
    private Long agamaId;

    @Size(max = 255)
    @Column(name = "jabatan_id")
    private String jabatanId;

    @Column(name = "status_penduduk")
    private Integer statusPenduduk;

    @Size(max = 30)
    @Column(name = "nik", length = 30)
    private String nik;

    @Column(name = "jenis_kelamin")
    private Integer jenisKelamin;

    @Size(max = 100)
    @Column(name = "tempat_lahir", length = 100)
    private String tempatLahir;

    @Column(name = "tanggal_lahir")
    private LocalDate tanggalLahir;

    @Size(max = 100)
    @Column(name = "pendidikan_terakhir", length = 100)
    private String pendidikanTerakhir;

    @Size(max = 100)
    @Column(name = "nip", length = 100)
    private String nip;

    @Size(max = 255)
    @Column(name = "pangkat")
    private String pangkat;

    @Size(max = 25)
    @Column(name = "nomor_telepon", length = 25)
    private String nomorTelepon;

    @Size(max = 255)
    @Column(name = "foto")
    private String foto;

    @Size(max = 100)
    @Column(name = "nomor_sk_pengangkatan", length = 100)
    private String nomorSkPengangkatan;

    @Column(name = "tanggal_sk_pengangkatan")
    private LocalDate tanggalSkPengangkatan;

    @Size(max = 255)
    @Column(name = "berkas_sk_pengangkatan")
    private String berkasSkPengangkatan;

    @Column(name = "status")
    private Boolean status;

    @Size(max = 100)
    @Column(name = "nomor_sk_pemberhentian", length = 100)
    private String nomorSkPemberhentian;

    @Column(name = "tanggal_sk_pemberhentian")
    private LocalDate tanggalSkPemberhentian;

    @Size(max = 255)
    @Column(name = "berkas_sk_pemberhentian")
    private String berkasSkPemberhentian;

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

    @Size(max = 4)
    @Column(name = "periode", length = 4)
    private String periode;

}