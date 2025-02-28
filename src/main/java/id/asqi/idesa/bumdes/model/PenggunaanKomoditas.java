package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "penggunaan_komoditas")
public class PenggunaanKomoditas {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "tanggal", nullable = false)
    private LocalDate tanggal;

    @NotNull
    @Column(name = "jumlah", nullable = false)
    private Integer jumlah;

    @NotNull
    @Column(name = "harga", nullable = false)
    private BigDecimal harga;

    @NotNull
    @Column(name = "deskripsi", nullable = false, length = Integer.MAX_VALUE)
    private String deskripsi;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_bumdes_id", nullable = false)
    private UserBumdes userBumdes;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "data_komoditas_tahunan_id", nullable = false)
    private DataKomoditasTahunan dataKomoditasTahunan;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private Instant tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}