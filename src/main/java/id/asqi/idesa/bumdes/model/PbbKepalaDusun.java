package id.asqi.idesa.bumdes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "pbb_kepala_dusun")
public class PbbKepalaDusun {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 30)
    @Column(name = "nik", length = 30)
    private String nik;

    @Size(max = 100)
    @Column(name = "nama", length = 100)
    private String nama;

    @Size(max = 15)
    @Column(name = "nomor_telepon", length = 15)
    private String nomorTelepon;

    @Size(max = 255)
    @Column(name = "alamat")
    private String alamat;

    @Size(max = 5)
    @Column(name = "rt", length = 5)
    private String rt;

    @Size(max = 5)
    @Column(name = "rw", length = 5)
    private String rw;

    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @Column(name = "alamat_desa_id")
    private Long alamatDesaId;

    @Column(name = "alamat_kecamatan_id")
    private Long alamatKecamatanId;

    @Column(name = "alamat_kabupaten_id")
    private Long alamatKabupatenId;

    @Column(name = "alamat_provinsi_id")
    private Long alamatProvinsiId;

    @Size(max = 100)
    @Column(name = "dibuat_oleh", length = 100)
    private String dibuatOleh;

    @Column(name = "tanggal_ditambah")
    private Instant tanggalDitambah;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}