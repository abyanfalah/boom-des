package id.asqi.idesa.bumdes.model;

import id.asqi.idesa.bumdes.model.UserBumdes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "ketersediaan_pangan_tahunan")
public class KetersediaanPanganTahunan {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "tahun", nullable = false)
    private Short tahun;

    @NotNull
    @Column(name = "jumlah_produksi", nullable = false)
    private Integer jumlahProduksi;

    @NotNull
    @Column(name = "total_kebutuhan", nullable = false)
    private Integer totalKebutuhan;

    @NotNull
    @Column(name = "persentase_keuntungan", nullable = false)
    private Integer persentaseKeuntungan;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_bumdes_id", nullable = false)
    private UserBumdes userBumdes;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private Instant tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}