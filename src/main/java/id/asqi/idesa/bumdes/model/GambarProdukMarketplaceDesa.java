package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gambar_produk_marketplace_desa")
public class GambarProdukMarketplaceDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produk_marketplace_desa_id", nullable = false)
    private ProdukMarketplaceDesa produkMarketplaceDesa;

    @Size(max = 255)
    @NotNull
    @Column(name = "url", nullable = false)
    private String url;

}