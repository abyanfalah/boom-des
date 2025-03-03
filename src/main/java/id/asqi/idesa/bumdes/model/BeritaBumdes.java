package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "berita_bumdes")
public class BeritaBumdes {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "judul")
    private String judul;

    @Column(name = "isi")
    private String isi;

    @Column(name = "is_ditayangkan")
    private Boolean isDitayangkan;

    @ManyToOne
    @JoinColumn(name = "user_bumdes_id", nullable = false)
    private UserBumdes userBumdes;

    @Column(name = "kata_kunci")
    private List<String> kataKunci;

    @ManyToOne
    @JoinColumn(name = "kategori_berita_bumdes_id", nullable = false)
    private KategoriBeritaBumdes kategoriBeritaBumdes;

    @Column(name = "tanggal_dibuat")
    private LocalDateTime tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private LocalDateTime tanggalDiubah;

}