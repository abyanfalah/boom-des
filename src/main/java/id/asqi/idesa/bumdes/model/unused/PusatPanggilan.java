package id.asqi.idesa.bumdes.model.unused;

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
@Table(name = "pusat_panggilan")
public class PusatPanggilan {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "foto")
    private String foto;

    @Size(max = 20)
    @Column(name = "nik", length = 20)
    private String nik;

    @Size(max = 100)
    @Column(name = "nama", length = 100)
    private String nama;

    @Column(name = "jenis_pusat_panggilan_id")
    private Long jenisPusatPanggilanId;

    @Size(max = 15)
    @Column(name = "nomor_whatsapp", length = 15)
    private String nomorWhatsapp;

    @Column(name = "peruntukan")
    private Integer peruntukan;

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

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

    @Column(name = "status")
    private Boolean status;

}