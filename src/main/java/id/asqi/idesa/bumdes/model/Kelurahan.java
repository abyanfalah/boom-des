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
@Table(name = "kelurahan")
public class Kelurahan {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 128)
    @NotNull
    @Column(name = "nama", nullable = false, length = 128)
    private String nama;

}