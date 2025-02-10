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
@Table(name = "mitra")
public class Mitra {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 16)
    @NotNull
    @Column(name = "nik", nullable = false, length = 16)
    private String nik;

    @Size(max = 255)
    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "alamat", nullable = false, length = Integer.MAX_VALUE)
    private String alamat;

    @Size(max = 20)
    @NotNull
    @Column(name = "no_telpon", nullable = false, length = 20)
    private String noTelpon;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jabatan_mitra_id", nullable = false)
    private JabatanMitra jabatanMitra;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jenis_mitra_id", nullable = false)
    private JenisMitra jenisMitra;

    @NotNull
    @Column(name = "is_aktif", nullable = false)
    private Boolean isAktif = false;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private Instant tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}