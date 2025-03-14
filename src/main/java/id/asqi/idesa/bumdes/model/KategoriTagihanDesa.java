package id.asqi.idesa.bumdes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import id.asqi.idesa.bumdes.enums.JenisPenagihan;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "kategori_tagihan_desa")
public class KategoriTagihanDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "kategori_dasar_tagihan_desa_id", nullable = false)
    private KategoriDasarTagihanDesa kategoriDasarTagihanDesa;

    @Size(max = 255)
    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "nominal_tagihan_bulanan")
    private BigDecimal nominalTagihanBulanan;

    @NotNull
    @Column(name = "biaya_admin_aplikasi", nullable = false)
    private BigDecimal biayaAdminAplikasi;

    @NotNull
    @Column(name = "biaya_admin_pengguna", nullable = false)
    private BigDecimal biayaAdminPengguna;

    @NotNull
    @Column(name = "biaya_admin_mitra_bumdes", nullable = false)
    private BigDecimal biayaAdminMitraBumdes;

    @NotNull
    @Column(name = "denda", nullable = false)
    private BigDecimal denda;

    @NotNull
    @Column(name = "satuan_denda", nullable = false)
    private Short satuanDenda;

    @NotNull
    @Column(name = "tanggal_penagihan", nullable = false)
    private Short tanggalPenagihan;

    @NotNull
    @Column(name = "tanggal_jatuh_tempo", nullable = false)
    private Short tanggalJatuhTempo;

    @NotNull
    @ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(name = "user_bumdes_id", nullable = false)
    private UserBumdes userBumdes;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "alamat_desa_id", nullable = false)
    private AlamatDesa alamatDesa;

    @ManyToOne
    @JoinColumn(name = "satuan_id")
    private Satuan satuan;

    @Column(name = "harga_per_satuan")
    private BigDecimal hargaPerSatuan;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    private LocalDateTime tanggalDibuat;

    @Column(name = "tanggal_diubah")
    private LocalDateTime tanggalDiubah;

    @NotNull
    @Column(name = "is_aktif", nullable = false)
    private Boolean isAktif = false;

    @NotNull
    @Column(name = "jenis_penagihan", nullable = false)
    private Short jenisPenagihan;

    @Column(name = "tanggal_dihapus")
    private LocalDateTime tanggalDihapus;

    public Boolean getIsPajak(){
        return this.kategoriDasarTagihanDesa.getIsPajak();
    }

    public String getJenisPenagihanLabel(){
        return JenisPenagihan.getString(this.jenisPenagihan);
    }
}