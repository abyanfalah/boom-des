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
@Table(name = "jenis_kegiatan")
public class JenisKegiatan {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "nama")
    private String nama;

}