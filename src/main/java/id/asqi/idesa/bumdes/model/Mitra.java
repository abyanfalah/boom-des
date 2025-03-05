package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "mitra")
public class Mitra {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "penduduk_id")
    private Penduduk penduduk;

    @ManyToOne
    @JoinColumn(name = "alamat_desa_id", nullable = false)
    private AlamatDesa alamatDesa;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "jabatan_mitra_id", nullable = false)
    private JabatanMitra jabatanMitra;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "jenis_mitra_id", nullable = false)
    private JenisMitra jenisMitra;

    @NotNull
    @Column(name = "is_aktif", nullable = false)
    private Boolean isAktif = false;

    @NotNull
    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private LocalDateTime tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private LocalDateTime tanggalDiubah;

}