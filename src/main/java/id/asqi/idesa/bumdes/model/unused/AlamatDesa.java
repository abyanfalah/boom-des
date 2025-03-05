package id.asqi.idesa.bumdes.model.unused;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alamat_desa")
public class AlamatDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "kecamatan_id")
    private AlamatKecamatan kecamatan;

    @Size(max = 100)
    @Column(name = "nama", length = 100)
    private String nama;

    @Column(name = "tipe")
    private Short tipe;

    @Column(name = "status")
    private Boolean status;

}