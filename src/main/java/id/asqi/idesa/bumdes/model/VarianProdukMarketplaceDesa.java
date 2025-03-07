package id.asqi.idesa.bumdes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// Varian Produk Marketplace Desa
@Entity
@Table(name = "varian_produk_marketplace_desa")
@Getter
@Setter
public class VarianProdukMarketplaceDesa {

	@Id
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "produk_marketplace_desa_id")
	@JsonBackReference
	private ProdukMarketplaceDesa produkMarketplaceDesa;

	@Column(name = "harga_jual_utama")
	private BigDecimal hargaJualUtama;

	@Column(name = "harga_modal")
	private BigDecimal hargaModal;

	@Column(name = "bobot_satuan")
	private Integer bobotSatuan;

	@ManyToOne
	@JoinColumn(name = "satuan_id")
	private Satuan satuan;

	@Column(name = "stok")
	private Integer stok;

	@Column(name = "sku")
	private String sku;

	@Column(name = "is_aktif")
	private Boolean isAktif;

	@Column(name = "is_utama")
	private Boolean isUtama;

	@Column(name = "tanggal_dibuat")
	private LocalDateTime tanggalDibuat;

	@Column(name = "tanggal_diubah")
	private LocalDateTime tanggalDiubah;

	@ManyToMany
	@JoinTable(
			name = "mapping_varian_produk_marketplace_desa",
			joinColumns = @JoinColumn(
					name = "varian_produk_marketplace_desa_id", referencedColumnName = "id"
			),
			inverseJoinColumns = @JoinColumn(
					name = "opsi_variasi_produk_marketplace_desa_id", referencedColumnName = "id"
			)
	)
	private List<OpsiVariasiProdukMarketplaceDesa> opsiVariasi;

}