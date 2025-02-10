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
@Table(name = "rukun_warga")
public class RukunWarga {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 20)
    @Column(name = "nik", length = 20)
    private String nik;

    @Size(max = 255)
    @Column(name = "nama")
    private String nama;

    @Column(name = "tanggal_lahir")
    private LocalDate tanggalLahir;

    @Size(max = 5)
    @Column(name = "nomor_rw", length = 5)
    private String nomorRw;

    @Column(name = "jenis_kelaim")
    private Integer jenisKelaim;

    @Size(max = 255)
    @Column(name = "alamat")
    private String alamat;

    @Size(max = 15)
    @Column(name = "nomor_telepon", length = 15)
    private String nomorTelepon;

    @Size(max = 255)
    @Column(name = "foto")
    private String foto;

    @Size(max = 100)
    @Column(name = "nomor_pengangkatan", length = 100)
    private String nomorPengangkatan;

    @Column(name = "tanggal_pengangkatan")
    private LocalDate tanggalPengangkatan;

    @Size(max = 255)
    @Column(name = "berkas_sk")
    private String berkasSk;

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

    @Size(max = 4)
    @Column(name = "periode", length = 4)
    private String periode;

}