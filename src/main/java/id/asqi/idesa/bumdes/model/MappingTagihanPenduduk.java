package id.asqi.idesa.bumdes.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "mapping_tagihan_penduduk")
@Getter
@Setter
public class MappingTagihanPenduduk {

	@Id
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "penduduk_id", nullable = false)
	private Penduduk penduduk;

	@NotNull
	@Column(name = "tanggal_dibuat")
	private LocalDateTime tanggalDibuat;

	@Column(name = "tanggal_diubah")
	private LocalDateTime tanggalDiubah;

	@Column(name = "tanggal_dihapus")
	private LocalDateTime tanggalDihapus;

	@ManyToMany
	@JoinTable(
			name = "mapping_tagihan_penduduk_kategori",
			joinColumns = @JoinColumn(
					name = "mapping_tagihan_penduduk_id", referencedColumnName = "id"
			),
			inverseJoinColumns = @JoinColumn(
					name = "kategori_tagihan_desa_id", referencedColumnName = "id"
			)
	)
	private List<KategoriTagihanDesa> kategoriTagihanAktif;

	@ManyToOne
	@JoinColumn(name = "user_bumdes_id", nullable = false)
	private UserBumdes userBumdes;

	@ManyToOne
	@JoinColumn(name = "alamat_desa_id", nullable = false)
	private AlamatDesa alamatDesa;

}