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
@Table(name = "panitia_pilkades")
public class PanitiaPilkades {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "penduduk_id")
    private Long pendudukId;

    @Size(max = 255)
    @Column(name = "keputusan")
    private String keputusan;

    @Size(max = 255)
    @Column(name = "catatan")
    private String catatan;

    @Size(max = 100)
    @Column(name = "dibuat_oleh", length = 100)
    private String dibuatOleh;

    @Column(name = "alamat_desa_id")
    private Long alamatDesaId;

    @Column(name = "alamat_kecamatan_id")
    private Long alamatKecamatanId;

    @Column(name = "alamat_kebupaten_id")
    private Long alamatKebupatenId;

    @Column(name = "alamat_provinsi_id")
    private Long alamatProvinsiId;

    @Column(name = "tanggal_ditambah")
    private Instant tanggalDitambah;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}