package id.asqi.idesa.bumdes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kategori_dasar_tagihan_desa")
public class KategoriDasarTagihanDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "siklus_bayar", nullable = false)
    private Short siklusBayar;

    @NotNull
    @Column(name = "is_pajak", nullable = false)
    private Boolean isPajak = false;

}