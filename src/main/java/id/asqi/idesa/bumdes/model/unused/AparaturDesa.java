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
@Table(name = "aparatur_desa")
public class AparaturDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "jabatan_aparatur_desa_id")
    private Long jabatanAparaturDesaId;

    @Size(max = 15)
    @Column(name = "nama", length = 15)
    private String nama;

    @Size(max = 255)
    @Column(name = "nipd")
    private String nipd;

    @Column(name = "tanggal_lahir")
    private LocalDate tanggalLahir;

    @Size(max = 15)
    @Column(name = "nomor_telepon", length = 15)
    private String nomorTelepon;

    @Column(name = "pendidikan_id")
    private Long pendidikanId;

    @Column(name = "jenis_kelamin")
    private Integer jenisKelamin;

    @Size(max = 255)
    @Column(name = "foto")
    private String foto;

    @Size(max = 100)
    @Column(name = "nomor_sk", length = 100)
    private String nomorSk;

    @Size(max = 255)
    @Column(name = "berkas_sk")
    private String berkasSk;

    @Size(max = 255)
    @Column(name = "alamat")
    private String alamat;

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

    @Column(name = "periode")
    private LocalDate periode;

    @Column(name = "tanggal_ditambah")
    private Instant tanggalDitambah;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}