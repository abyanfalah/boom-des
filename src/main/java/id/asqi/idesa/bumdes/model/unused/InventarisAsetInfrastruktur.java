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
@Table(name = "inventaris_aset_infrastruktur")
public class InventarisAsetInfrastruktur {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "nup")
    private String nup;

    @Size(max = 10)
    @Column(name = "tahun_perolehan", length = 10)
    private String tahunPerolehan;

    @Size(max = 255)
    @Column(name = "nama")
    private String nama;

    @Column(name = "panjang")
    private Integer panjang;

    @Column(name = "lebar")
    private Integer lebar;

    @Column(name = "luas")
    private Integer luas;

    @Column(name = "asal_perolehan_id")
    private Long asalPerolehanId;

    @Size(max = 255)
    @Column(name = "letak")
    private String letak;

    @Size(max = 255)
    @Column(name = "status_tanah")
    private String statusTanah;

    @Size(max = 255)
    @Column(name = "penggunaan")
    private String penggunaan;

    @Column(name = "nilai_perolehan")
    private Integer nilaiPerolehan;

    @Column(name = "golongan_id")
    private Long golonganId;

    @Column(name = "bidang_id")
    private Long bidangId;

    @Column(name = "kelompok_id")
    private Long kelompokId;

    @Column(name = "sub_kelompok_id")
    private Long subKelompokId;

    @Column(name = "kondisi_barang_awal_tahun")
    private Integer kondisiBarangAwalTahun;

    @Column(name = "kondisi_barang_akhir_tahun")
    private Integer kondisiBarangAkhirTahun;

    @Column(name = "tanggal_penghapusan")
    private LocalDate tanggalPenghapusan;

    @Column(name = "jumlah_dihapus")
    private Integer jumlahDihapus;

    @Column(name = "sebab_id")
    private Long sebabId;

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