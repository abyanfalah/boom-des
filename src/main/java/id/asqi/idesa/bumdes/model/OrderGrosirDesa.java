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
@Table(name = "order_grosir_desa")
public class OrderGrosirDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "penduduk_id", nullable = false)
    private Penduduk penduduk;

    @NotNull
    @Column(name = "status", nullable = false)
    private Short status;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "metode_pengiriman_id", nullable = false)
    private MetodePengiriman metodePengiriman;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "metode_pembayaran_id", nullable = false)
    private MetodePembayaran metodePembayaran;

    @Size(max = 20)
    @NotNull
    @Column(name = "nomor_hp", nullable = false, length = 20)
    private String nomorHp;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private Instant tanggalDibuat;

}