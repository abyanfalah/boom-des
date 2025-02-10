package id.asqi.idesa.bumdes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pbb_wajib_pajak")
public class PbbWajibPajak {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "pbb_kepala_dusun_id")
    private Long pbbKepalaDusunId;

    @Size(max = 100)
    @Column(name = "nop", length = 100)
    private String nop;

    @Size(max = 30)
    @Column(name = "nik", length = 30)
    private String nik;

    @Size(max = 100)
    @Column(name = "nama", length = 100)
    private String nama;

    @Size(max = 100)
    @Column(name = "nama_pemilik_baru", length = 100)
    private String namaPemilikBaru;

    @Size(max = 255)
    @Column(name = "alamat_wajib_pajak")
    private String alamatWajibPajak;

    @Column(name = "alamat_desa_wajib_pajak_id")
    private Long alamatDesaWajibPajakId;

    @Size(max = 255)
    @Column(name = "alamat_objek_pajak")
    private String alamatObjekPajak;

    @Column(name = "alamat_kecamatan_objek_pajak_id")
    private Long alamatKecamatanObjekPajakId;

    @Column(name = "alamat_desa_objek_pajak_id")
    private Long alamatDesaObjekPajakId;

    @Size(max = 5)
    @Column(name = "rw_objek_pajak", length = 5)
    private String rwObjekPajak;

    @Size(max = 5)
    @Column(name = "rt_objek_pajak", length = 5)
    private String rtObjekPajak;

    @Column(name = "luas_bumi")
    private Integer luasBumi;

    @Column(name = "luas_bangunan")
    private Integer luasBangunan;

    @Column(name = "nilai_jual_objek_pajak_bumi")
    private Long nilaiJualObjekPajakBumi;

    @Column(name = "nilai_jual_objek_pajak_bangunan")
    private Long nilaiJualObjekPajakBangunan;

    @Size(max = 4)
    @Column(name = "tahun_pajak", length = 4)
    private String tahunPajak;

    @Column(name = "tanggal_jatuh_tempo")
    private LocalDate tanggalJatuhTempo;

    @Column(name = "pokok")
    private Long pokok;

    @Column(name = "denda")
    private Long denda;

    @Size(max = 100)
    @Column(name = "dibuat_oleh", length = 100)
    private String dibuatOleh;

    @Column(name = "metode_pembayaran")
    private Integer metodePembayaran;

    @Column(name = "status")
    private Integer status;

    @Column(name = "tanggal_bayar")
    private LocalDate tanggalBayar;

    @Column(name = "tanggal_ditambah")
    private Instant tanggalDitambah;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}