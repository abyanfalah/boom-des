package id.asqi.idesa.bumdes.model.unused;

import id.asqi.idesa.bumdes.model.unused.AsalBarang;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "inventaris_bpd")
public class InventarisBpd {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "jenis")
    private String jenis;

    @Column(name = "tanggal_perolehan")
    private LocalDate tanggalPerolehan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asal_barang_id")
    private AsalBarang asalBarang;

    @Column(name = "kondisi_awal_tahun")
    private Integer kondisiAwalTahun;

    @Column(name = "kondisi_akhir_tahun")
    private Integer kondisiAkhirTahun;

    @Size(max = 255)
    @Column(name = "alasan_penghapusan")
    private String alasanPenghapusan;

    @Column(name = "tanggal_penghapusan")
    private LocalDate tanggalPenghapusan;

    @Size(max = 255)
    @Column(name = "foto")
    private String foto;

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