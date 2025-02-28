package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

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

    @Size(max = 20)
    @NotNull
    @Column(name = "no_telpon", nullable = false, length = 20)
    private String noTelpon;

    @Size(max = 255)
    @Column(name = "url_foto_profil")
    private String urlFotoProfil;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "metode_pengiriman_id", nullable = false)
    private MetodePengiriman metodePengiriman;

    @NotNull
    @Column(name = "alamat_lengkap", nullable = false, length = Integer.MAX_VALUE)
    private String alamatLengkap;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}