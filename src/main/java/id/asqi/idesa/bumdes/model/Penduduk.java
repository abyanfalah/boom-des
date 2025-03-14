package id.asqi.idesa.bumdes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    @JsonBackReference
    @JoinColumn(name = "alamat_desa_id", nullable = false)
    private AlamatDesa alamatDesa;

//    @Column(name = "tanggal_dibuat", nullable = false)
//    private LocalDateTime tanggalDibuat;
//
//    @Column(name = "tanggal_diubah")
//    private LocalDateTime tanggalDiubah;
//
//    @Column(name = "tanggal_dihapus")
//    private LocalDateTime tanggalDihapus;
}