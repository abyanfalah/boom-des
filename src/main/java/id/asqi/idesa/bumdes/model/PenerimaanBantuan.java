package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "penerimaan_bantuan")
public class PenerimaanBantuan {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "jenis_bantuan", nullable = false)
    private Short jenisBantuan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id")
    private VoucherBantuan voucher;

    @NotNull
    @Column(name = "user_bumdes_id", nullable = false)
    private Long userBumdesId;

    @Column(name = "nominal_per_orang")
    private Long nominalPerOrang;

    @NotNull
    @Column(name = "status", nullable = false)
    private Short status;

    @Column(name = "url_foto_bukti", length = Integer.MAX_VALUE)
    private String urlFotoBukti;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private Instant tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}