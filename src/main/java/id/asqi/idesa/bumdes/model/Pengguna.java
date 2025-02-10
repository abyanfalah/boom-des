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

@Getter
@Setter
@Entity
@Table(name = "pengguna")
public class Pengguna {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "peran")
    private Short peran;

    @Size(max = 255)
    @Column(name = "nama_lengkap")
    private String namaLengkap;

    @Size(max = 255)
    @Column(name = "nama_pengguna")
    private String namaPengguna;

    @Size(max = 255)
    @Column(name = "kata_sandi")
    private String kataSandi;

    @Column(name = "status")
    private Boolean status;

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