//package id.asqi.idesa.bumdes.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "mapping_varian_produk_marketplace_desa")
//public class MappingVarianProdukMarketplaceDesa {
//    @NotNull
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "varian_produk_marketplace_desa_id", nullable = false)
//    private VarianProdukMarketplaceDesa varianProdukMarketplaceDesa;
//
//    @NotNull
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "opsi_variasi_produk_marketplace_desa_id", nullable = false)
//    private OpsiVariasiProdukMarketplaceDesa opsiVariasiProdukMarketplaceDesa;
//
//}