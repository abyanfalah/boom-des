package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "opsi_variasi_produk_marketplace_desa")
public class OpsiVariasiProdukMarketplaceDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "jenis_variasi_produk_marketplace_desa_id", nullable = false)
    private JenisVariasiProdukMarketplaceDesa jenisVariasiProdukMarketplaceDesa;

    @Size(max = 128)
    @NotNull
    @Column(name = "nama", nullable = false, length = 128)
    private String nama;

}