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

@Getter
@Setter
@Entity
@Table(name = "kpm")
public class Kpm {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 200)
    @Column(name = "nama", length = 200)
    private String nama;

    @Column(name = "umur")
    private Integer umur;

    @Column(name = "jenis_kelamin")
    private Integer jenisKelamin;

    @Size(max = 255)
    @Column(name = "pendidikan")
    private String pendidikan;

    @Size(max = 255)
    @Column(name = "bidang")
    private String bidang;

    @Size(max = 255)
    @Column(name = "alamat")
    private String alamat;

    @Size(max = 255)
    @Column(name = "keterangan")
    private String keterangan;

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

}