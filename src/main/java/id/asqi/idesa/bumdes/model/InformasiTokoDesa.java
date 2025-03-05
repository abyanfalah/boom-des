package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "informasi_toko_desa")
public class InformasiTokoDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;


    @OneToOne()
    @JoinColumn(name = "alamat_desa_id")
    private AlamatDesa alamatDesa;

    @Size(max = 20)
    @NotNull
    @Column(name = "no_telpon", nullable = false, length = 20)
    private String noTelpon;

    @Size(max = 255)
    @Column(name = "url_foto_profil")
    private String urlFotoProfil;


    @ManyToMany
    @JoinTable(
            name = "metode_pengiriman_toko_desa",
            joinColumns = @JoinColumn(
                    name = "informasi_toko_desa_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "metode_pengiriman_id", referencedColumnName = "id"
            )
    )
    private List<MetodePengiriman> metodePengiriman;

    @NotNull
    @Column(name = "alamat_lengkap", nullable = false, length = Integer.MAX_VALUE)
    private String alamatLengkap;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;


    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}