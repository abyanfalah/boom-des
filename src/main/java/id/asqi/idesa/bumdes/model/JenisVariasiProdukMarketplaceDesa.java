package id.asqi.idesa.bumdes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "jenis_variasi_produk_marketplace_desa")
public class JenisVariasiProdukMarketplaceDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "produk_marketplace_desa_id", nullable = false)
    @JsonBackReference
    private ProdukMarketplaceDesa produkMarketplaceDesa;

    @Size(max = 128)
    @NotNull
    @Column(name = "nama", nullable = false, length = 128)
    private String nama;

    @OneToMany(mappedBy = "jenisVariasiProdukMarketplaceDesa")
    @JsonManagedReference
    private List<OpsiVariasiProdukMarketplaceDesa> opsiVariasi;
}