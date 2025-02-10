package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "detail_order_grosir_desa")
public class DetailOrderGrosirDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_grosir_desa_id", nullable = false)
    private OrderGrosirDesa orderGrosirDesa;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produk_grosir_desa_id", nullable = false)
    private ProdukGrosir produkGrosirDesa;

    @NotNull
    @Column(name = "harga", nullable = false)
    private Long harga;

    @NotNull
    @Column(name = "qty", nullable = false)
    private Short qty;

}