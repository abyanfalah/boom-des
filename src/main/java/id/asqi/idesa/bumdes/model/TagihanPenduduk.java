package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tagihan_penduduk")
@Getter
@Setter
public class TagihanPenduduk {

	@Id
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "penduduk_id")
	private Penduduk penduduk;

	@ManyToOne
	@JoinColumn(name = "kategori_tagihan_desa_id")
	private KategoriTagihanDesa kategoriTagihanDesa;

	@Column(name = "tahun")
	private Short tahun;

	@Column(name = "bulan")
	private Short bulan;

	@Column(name = "pemakaian_satuan")
	private Integer pemakaianSatuan;

	@Column(name = "total_potongan")
	private BigDecimal totalPotongan;

	@Column(name = "total_tagihan")
	private BigDecimal totalTagihan;

	@Column(name = "status")
	private Short status;

	@ManyToOne
	@JoinColumn(name = "user_bumdes_id")
	private UserBumdes userBumdes;

	@ManyToOne
	@JoinColumn(name = "alamat_desa_id", nullable = false)
	private AlamatDesa alamatDesa;

	@Column(name = "tanggal_dibuat")
	private LocalDateTime tanggalDibuat;

	@Column(name = "tanggal_diubah")
	private LocalDateTime tanggalDiubah;



}