package id.asqi.idesa.bumdes.model;

import id.asqi.idesa.bumdes.model.UserBumdes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kategori_produk_marketplace_desa_id", nullable = false)
    private KategoriProdukMarketplaceDesa kategoriProdukMarketplaceDesa;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_bumdes_id", nullable = false)
    private UserBumdes userBumdes;

    @NotNull
    @Column(name = "kondisi", nullable = false)
    private Short kondisi;

    @NotNull
    @Column(name = "deskripsi", nullable = false, length = Integer.MAX_VALUE)
    private String deskripsi;

    @NotNull
    @Column(name = "is_aktif", nullable = false)
    private Boolean isAktif = false;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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
    private Instant tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}