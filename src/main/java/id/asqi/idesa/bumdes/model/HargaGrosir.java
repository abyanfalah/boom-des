package id.asqi.idesa.bumdes.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "harga_grosir")
@Getter
@Setter
public class HargaGrosir {

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "produk_marketplace_desa_id")
	private ProdukMarketplaceDesa produkMarketplaceDesa;

	@ManyToOne
	@JoinColumn(name = "varian_produk_marketplace_desa_id")
	private VarianProdukMarketplaceDesa varianProdukMarketplaceDesa;

	@Column(name = "kuantitas_minimum")
	private Integer kuantitasMinimum;

	@Column(name = "harga")
	private BigDecimal harga;
}