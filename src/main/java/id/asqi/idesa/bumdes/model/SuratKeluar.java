package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "surat")
@Getter
@Setter
public class SuratKeluar {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "tipe_surat")
	private Short tipeSurat;

	@Column(name = "tanggal_penerimaan_surat")
	@Temporal(TemporalType.DATE)
	private Date tanggalPenerimaanSurat;

	@Column(name = "nomor_surat")
	private String nomorSurat;

	@Column(name = "tanggal_surat")
	@Temporal(TemporalType.DATE)
	private Date tanggalSurat;

	@Column(name = "pengirim")
	private String pengirim;



	@Column(name = "isi_singkat")
	private String isiSingkat;

	@Column(name = "disposisi_surat")
	private String disposisiSurat;

	@Column(name = "uraian_disposisi")
	private String uraianDisposisi;

	@Column(name = "status")
	private Integer status;

	@Column(name = "berkas")
	private Boolean berkas;

	@Column(name = "dibaca")
	private Boolean dibaca;

	@ManyToOne  // Assuming many Surats to one SifatSurat
	@JoinColumn(name = "sifat_surat_id") // Name of the foreign key column
	private SifatSurat sifatSurat;

	@ManyToOne  // Assuming many Surats to one AlamatDesa
	@JoinColumn(name = "alamat_desa_id")
	private AlamatDesa alamatDesa;

	@ManyToOne  // Assuming many Surats to one AlamatKecamatan
	@JoinColumn(name = "alamat_kecamatan_id")
	private AlamatKecamatan alamatKecamatan;

	@ManyToOne  // Assuming many Surats to one AlamatKabupaten
	@JoinColumn(name = "alamat_kabupaten_id")
	private AlamatKabupaten alamatKabupaten;

	@ManyToOne  // Assuming many Surats to one AlamatProvinsi
	@JoinColumn(name = "alamat_provinsi_id")
	private AlamatProvinsi alamatProvinsi;
}