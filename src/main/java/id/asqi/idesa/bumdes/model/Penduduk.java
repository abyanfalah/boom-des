package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "penduduk")
public class Penduduk {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 16)
    @Column(name = "nik", length = 16)
    private String nik;

    @Size(max = 255)
    @Column(name = "nama")
    private String nama;

    @ManyToOne
    @JoinColumn(name = "alamat_desa_id", nullable = false)
    private AlamatDesa alamatDesa;

    @ManyToMany
    @JoinTable(
            name = "mapping_penduduk_kategori_tagihan",
            joinColumns = @JoinColumn(
                    name = "penduduk_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "kategori_tagihan_desa_id", referencedColumnName = "id"
            )
    )
    private List<KategoriTagihanDesa> kategoriTagihanAktif;


}