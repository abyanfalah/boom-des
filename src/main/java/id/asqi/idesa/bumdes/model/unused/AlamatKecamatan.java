package id.asqi.idesa.bumdes.model.unused;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alamat_kecamatan")
public class AlamatKecamatan {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "kabupaten_id")
    private AlamatKabupaten kabupaten;

    @Size(max = 100)
    @Column(name = "nama", length = 100)
    private String nama;

}