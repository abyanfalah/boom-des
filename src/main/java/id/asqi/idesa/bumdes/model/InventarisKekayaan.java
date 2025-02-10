package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "inventaris_kekayaan")
public class InventarisKekayaan {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "nup")
    private String nup;

    @Size(max = 10)
    @Column(name = "tahun_perolehan", length = 10)
    private String tahunPerolehan;

    @Column(name = "tipe_inventaris")
    private Integer tipeInventaris;

    @Size(max = 100)
    @Column(name = "nama", length = 100)
    private String nama;

    @Column(name = "luas")
    private Integer luas;

    @Size(max = 5)
    @Column(name = "satuan", length = 5)
    private String satuan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asal_perolehan_id")
    private AsalPerolehan asalPerolehan;

    @Size(max = 255)
    @Column(name = "letak_barang")
    private String letakBarang;

    @Size(max = 255)
    @Column(name = "bukti__kepemilikan")
    private String buktiKepemilikan;

    @Size(max = 255)
    @Column(name = "penggunaan")
    private String penggunaan;

    @Column(name = "nilai_perolehan")
    private Integer nilaiPerolehan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "golongan_id")
    private Golongan golongan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bidang_id")
    private Bidang bidang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kelompok_id")
    private Kelompok kelompok;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_kelompok_id")
    private SubKelompok subKelompok;

    @Column(name = "kondisi_barang_awal")
    private Integer kondisiBarangAwal;

    @Column(name = "kondisi_barang_akhir_tahun")
    private Integer kondisiBarangAkhirTahun;

    @Column(name = "tanggal_penghapusan")
    private LocalDate tanggalPenghapusan;

    @Column(name = "jumlah_dihapus")
    private Integer jumlahDihapus;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "sebab_id")
//    private SebabPenghapusan sebab;

    @Column(name = "tanggal_penambahan")
    private LocalDate tanggalPenambahan;

    @Column(name = "jumlah_bertambah")
    private Integer jumlahBertambah;

    @Size(max = 255)
    @Column(name = "keterangan")
    private String keterangan;

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