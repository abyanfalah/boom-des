package id.asqi.idesa.bumdes.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "produk_marketplace_desa")
public class ProdukMarketplaceDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "kategori_produk_marketplace_desa_id", nullable = false)
    private KategoriProdukMarketplaceDesa kategoriProdukMarketplaceDesa;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_bumdes_id", nullable = false)
    private UserBumdes userBumdes;

    @ManyToOne
    @JoinColumn(name = "alamat_desa_id", nullable = false)
    private AlamatDesa alamatDesa;

    @NotNull
    @Column(name = "kondisi", nullable = false)
    private Short kondisi;

    @NotNull
    @Column(name = "deskripsi", nullable = false, length = Integer.MAX_VALUE)
    private String deskripsi;

    @NotNull
    @Column(name = "is_aktif", nullable = false)
    private Boolean isAktif = false;


    @Column(name = "harga_jual_utama")
    private BigDecimal hargaJualUtama;

    @Column(name = "harga_modal")
    private BigDecimal hargaModal;

    @Column(name = "fee_aplikasi")
    private BigDecimal feeAplikasi;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "satuan_id", nullable = false)
    private Satuan satuan;

    @NotNull
    @Column(name = "bobot_satuan", nullable = false)
    private Integer bobotSatuan;

    @NotNull
    @Column(name = "stok", nullable = false)
    private Integer stok;

    @Size(max = 128)
    @NotNull
    @Column(name = "sku", nullable = false, length = 128)
    private String sku;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private LocalDateTime tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private LocalDateTime tanggalDiubah;

    @NotNull
    @Column(name = "has_varian", nullable = false)
    private Boolean hasVarian = false;

    @OneToMany(mappedBy = "produkMarketplaceDesa")
    @JsonManagedReference
    private List<GambarProdukMarketplaceDesa> urlGambar;

    @OneToMany(mappedBy = "produkMarketplaceDesa")
    @JsonManagedReference
    private List<HargaGrosir> hargaGrosir;

    @OneToMany(mappedBy = "produkMarketplaceDesa")
    @JsonManagedReference
    private List<VarianProdukMarketplaceDesa> varian;

    @OneToMany(mappedBy = "produkMarketplaceDesa")
    @JsonManagedReference
    private List<JenisVariasiProdukMarketplaceDesa> jenisVariasi;

    @Column(name = "tanggal_dihapus")
    private LocalDateTime tanggalDihapus;

    /*Gemini suggested to sort from the frontend.*/
//    public List<HargaGrosir> getHargaGrosir() {
//        if (hargaGrosir == null || hargaGrosir.isEmpty()) {
//            return hargaGrosir;
//        }
//
//        List<HargaGrosir> sortedHargaGrosir = new ArrayList<>(hargaGrosir);
//        sortedHargaGrosir.sort(Comparator.comparingInt(HargaGrosir::getKuantitasMinimum));
//        return sortedHargaGrosir;
//    }
}