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
@Table(name = "keputusan_kades")
public class KeputusanKades {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "tentang")
    private String tentang;

    @Size(max = 255)
    @Column(name = "nomor")
    private String nomor;

    @Size(max = 10)
    @Column(name = "tahun", length = 10)
    private String tahun;

    @Column(name = "tanggal_ditetapkan")
    private LocalDate tanggalDitetapkan;

    @Size(max = 255)
    @Column(name = "uraian")
    private String uraian;

    @Size(max = 255)
    @Column(name = "berkas")
    private String berkas;

    @Size(max = 255)
    @Column(name = "dibuat_oleh")
    private String dibuatOleh;

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

    @Column(name = "tanggal_diuabah")
    private Instant tanggalDiuabah;

    @Size(max = 4)
    @Column(name = "periode", length = 4)
    private String periode;

}