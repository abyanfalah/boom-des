package id.asqi.idesa.bumdes.model.unused;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "absensi")
public class Absensi {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "pegawai_desa_id")
    private Long pegawaiDesaId;

    @Column(name = "jam_masuk")
    private LocalTime jamMasuk;

    @Column(name = "jam_pulang")
    private LocalTime jamPulang;

    @Column(name = "jam_lembur")
    private LocalTime jamLembur;

    @Column(name = "jenis_absen")
    private Integer jenisAbsen;

    @Column(name = "status")
    private Integer status;

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