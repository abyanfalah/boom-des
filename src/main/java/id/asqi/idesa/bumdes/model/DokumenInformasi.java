package id.asqi.idesa.bumdes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "dokumen_informasi")
public class DokumenInformasi {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "url", nullable = false, length = Integer.MAX_VALUE)
    private String url;

    @NotNull
    @ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(name = "informasi_id", nullable = false)
    private Informasi informasi;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private LocalDateTime tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private LocalDateTime tanggalDiubah;

}