package id.asqi.idesa.bumdes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @JsonBackReference
    private JenisVariasiProdukMarketplaceDesa jenisVariasiProdukMarketplaceDesa;

    @NotNull
    @Column(name = "nama", nullable = false, length = 128)
    private String nama;
}