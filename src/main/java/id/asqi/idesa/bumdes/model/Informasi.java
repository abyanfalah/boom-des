package id.asqi.idesa.bumdes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "informasi")
public class Informasi {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "judul", nullable = false, length = Integer.MAX_VALUE)
    private String judul;

    @Column(name = "isi")
    private String isi;

    @OneToMany(mappedBy = "informasi")
    @JsonManagedReference
    private List<GambarInformasi> gambar;

    @OneToMany(mappedBy = "informasi")
    @JsonManagedReference
    private List<DokumenInformasi> dokumen;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_bumdes_id", nullable = false)
    private UserBumdes userBumdes;

    @ManyToOne
    @JoinColumn(name = "alamat_desa_id", nullable = false)
    private AlamatDesa alamatDesa;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private LocalDateTime tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private LocalDateTime tanggalDiubah;
}