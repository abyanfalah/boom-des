package id.asqi.idesa.bumdes.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import id.asqi.idesa.bumdes.http.request.ProdukMarketplaceDesaRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "harga_grosir")
@Getter
@Setter
@NoArgsConstructor
public class HargaGrosir {

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JsonBackReference
	@JsonIgnore
	@JoinColumn(name = "produk_marketplace_desa_id")
	private ProdukMarketplaceDesa produkMarketplaceDesa;

	@ManyToOne
	@JsonBackReference
	@JsonIgnore
	@JoinColumn(name = "varian_produk_marketplace_desa_id")
	private VarianProdukMarketplaceDesa varianProdukMarketplaceDesa;

	@Column(name = "kuantitas_minimum")
	private Integer kuantitasMinimum;

	@Column(name = "harga")
	private BigDecimal harga;



}