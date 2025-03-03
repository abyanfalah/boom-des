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
@Table(name = "lembaga_kemasyarakatan")
public class LembagaKemasyarakatan {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "penduduk_id")
    private Long pendudukId;

    @Column(name = "jabatan_id")
    private Long jabatanId;

    @Column(name = "jenis_lembaga_id")
    private Long jenisLembagaId;

    @Size(max = 15)
    @Column(name = "nomor_telepon", length = 15)
    private String nomorTelepon;

    @Size(max = 255)
    @Column(name = "foto")
    private String foto;

    @Size(max = 100)
    @Column(name = "nomor_sk", length = 100)
    private String nomorSk;

    @Column(name = "tanggal_keluar_sk")
    private LocalDate tanggalKeluarSk;

    @Size(max = 255)
    @Column(name = "berkas")
    private String berkas;

    @Size(max = 255)
    @Column(name = "dibuat_oleh")
    private String dibuatOleh;

    @Size(max = 4)
    @Column(name = "periode", length = 4)
    private String periode;

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

    @Column(name = "status")
    private Boolean status;

}