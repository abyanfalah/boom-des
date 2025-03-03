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
@Table(name = "penyedia_informasi_publik")
public class PenyediaInformasiPublik {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tanggal")
    private LocalDate tanggal;

    @Column(name = "jenis_informasi_id")
    private Long jenisInformasiId;

    @Column(name = "media_penyampaian_informasi")
    private Integer mediaPenyampaianInformasi;

    @Size(max = 255)
    @Column(name = "tempat_informasi")
    private String tempatInformasi;

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

    @Column(name = "tanggal_dibuat")
    private Instant tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}