package id.asqi.idesa.bumdes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jenis_informasi")
public class JenisInformasi {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "nama", length = 100)
    private String nama;

}