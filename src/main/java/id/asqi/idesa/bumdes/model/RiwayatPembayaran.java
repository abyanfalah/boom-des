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
@Table(name = "riwayat_pembayaran")
public class RiwayatPembayaran {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "pbb_wajib_pajak_id")
    private Long pbbWajibPajakId;

    @Size(max = 4)
    @Column(name = "periode", length = 4)
    private String periode;

    @Column(name = "jumlah_nilai_objek_pajak")
    private Long jumlahNilaiObjekPajak;

    @Column(name = "nominal")
    private Long nominal;

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

}