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
@Table(name = "jabatan_mitra")
public class JabatanMitra {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 64)
    @NotNull
    @Column(name = "nama", nullable = false, length = 64)
    private String nama;

}