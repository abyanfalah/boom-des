package id.asqi.idesa.bumdes.model;

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
@Table(name = "produk_grosir")
public class ProdukGrosir {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "kategori_produk_grosir_id", nullable = false)
    private KategoriProdukGrosir kategoriProdukGrosir;

    @OneToMany(mappedBy = "produkGrosir")
    private List<GambarProdukGrosir> gambarProdukGrosir;

    @NotNull
    @Column(name = "kondisi", nullable = false)
    private Short kondisi;

    @NotNull
    @Column(name = "stok", nullable = false)
    private Integer stok;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "satuan_id", nullable = false)
    private Satuan satuan;

    @NotNull
    @Column(name = "berat_satuan_kg", nullable = false)
    private Integer beratSatuanKg;

    @NotNull
    @Column(name = "harga", nullable = false)
    private BigDecimal harga;

    @NotNull
    @Column(name = "harga_diskon", nullable = false)
    private BigDecimal hargaDiskon;

    @NotNull
    @Column(name = "is_aktif", nullable = false)
    private Boolean isAktif = false;

    @Column(name = "deskripsi", length = Integer.MAX_VALUE)
    private String deskripsi;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_bumdes_id", nullable = false)
    private UserBumdes userBumdes;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private LocalDateTime tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private LocalDateTime tanggalDiubah;

}