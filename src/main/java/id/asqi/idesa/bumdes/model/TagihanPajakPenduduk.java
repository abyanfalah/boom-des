package id.asqi.idesa.bumdes.model;

import id.asqi.idesa.bumdes.model.unused.Kelurahan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tagihan_pajak_penduduk")
@Getter
@Setter
public class TagihanPajakPenduduk {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "nomor_objek_pajak")
	private String nomorObjekPajak;

	@ManyToOne
	@JoinColumn(name = "kategori_tagihan_desa_id")
	private KategoriTagihanDesa kategoriTagihanDesa;

	@Column(name = "nama_wajib_pajak")
	private String namaWajibPajak;

	@Column(name = "alamat_wajib_pajak")
	private String alamatWajibPajak;

	@Column(name = "luas_tanah_meter_persegi")
	private Integer luasTanahMeterPersegi;

	@Column(name = "luas_bangunan_meter_persegi")
	private Integer luasBangunanMeterPersegi;


	@ManyToOne
	@JoinColumn(name = "kelurahan_id")
	private Kelurahan kelurahan;

	@Column(name = "tahun")
	private Short tahun;

	@Column(name = "tanggal_jatuh_tempo")
	@Temporal(TemporalType.DATE)
	private Date tanggalJatuhTempo;

	@Column(name = "total_tagihan")
	private BigDecimal totalTagihan;

	@Column(name = "biaya_tambahan")
	private BigDecimal biayaTambahan;

	@Column(name = "denda")
	private BigDecimal denda;

	@Column(name = "status")
	private Short status;

	@ManyToOne
	@JoinColumn(name = "alamat_desa_id", nullable = false)
	private AlamatDesa alamatDesa;

	@ManyToOne
	@JoinColumn(name = "user_bumdes_id")
	private UserBumdes userBumdes;

	@Column(name = "tanggal_dibuat")
	private LocalDateTime tanggalDibuat;

	@Column(name = "tanggal_diubah")
	private LocalDateTime tanggalDiubah;

}