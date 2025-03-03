package id.asqi.idesa.bumdes.model.unused;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alamat_kabupaten")
public class AlamatKabupaten {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinsi_id")
    private AlamatProvinsi provinsi;

    @Size(max = 100)
    @Column(name = "nama", length = 100)
    private String nama;

    @Column(name = "tipe")
    private Short tipe;

}