package id.asqi.idesa.bumdes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import id.asqi.idesa.bumdes.enums.StatusTagihanPenduduk;
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

	// TODO: add total tagihan yang harus dibayar. (tagihan after potongan)

	@Column(name = "status")
	private Short status = StatusTagihanPenduduk.BELUM_LUNAS;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "user_bumdes_id")
	private UserBumdes userBumdes;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "alamat_desa_id", nullable = false)
	private AlamatDesa alamatDesa;

	@Column(name = "tanggal_dibuat")
	private LocalDateTime tanggalDibuat;

	@Column(name = "tanggal_diubah")
	private LocalDateTime tanggalDiubah;

	@Column(name = "tanggal_dihapus")
	private LocalDateTime tanggalDihapus;

	@ManyToOne
	@JoinColumn(name = "mapping_tagihan_penduduk_id")
	private MappingTagihanPenduduk mappingTagihan;
}