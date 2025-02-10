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
@Table(name = "data_komoditas_tahunan")
public class DataKomoditasTahunan {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "sumber", nullable = false)
    private Short sumber;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "komoditas_id", nullable = false)
    private Komoditas komoditas;

    @NotNull
    @Column(name = "siklus", nullable = false)
    private Short siklus;

    @NotNull
    @Column(name = "jumlah", nullable = false)
    private Integer jumlah;

    @NotNull
    @Column(name = "tahun", nullable = false)
    private Short tahun;

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