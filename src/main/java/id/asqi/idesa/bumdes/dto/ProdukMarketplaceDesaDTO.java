package id.asqi.idesa.bumdes.dto;

import id.asqi.idesa.bumdes.model.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProdukMarketplaceDesaDTO {
	private Long id;
	private String nama;
	private List<String> urlGambar;
	private KategoriProdukMarketplaceDesa kategoriProdukMarketplaceDesa;
	private UserBumdes userBumdes;
	private AlamatDesa alamatDesa;
	private Short kondisi;
	private String deskripsi;
	private Boolean isAktif;
	private BigDecimal hargaJualUtama;
	private BigDecimal hargaModal;
	private BigDecimal feeAplikasi;
	private Satuan satuan;
	private Integer bobotSatuan;
	private Integer stok;
	private String sku;
	private LocalDateTime tanggalDibuat;
	private LocalDateTime tanggalDiubah;
	private Boolean hasVarian;
	private List<HargaGrosir> hargaGrosir;
	private List<JenisVariasiProdukMarketplaceDesaDTO> jenisVariasi;
	private List<VarianProdukMarketplaceDesaDTO> varian;

	public ProdukMarketplaceDesaDTO(ProdukMarketplaceDesa p) {
		this.id = p.getId();
		this.nama = p.getNama();
		this.kategoriProdukMarketplaceDesa = p.getKategoriProdukMarketplaceDesa();
		this.userBumdes = p.getUserBumdes();
		this.alamatDesa = p.getAlamatDesa();
		this.kondisi = p.getKondisi();
		this.deskripsi = p.getDeskripsi();
		this.isAktif = p.getIsAktif();
		this.hargaJualUtama = p.getHargaJualUtama();
		this.hargaModal = p.getHargaModal();
		this.feeAplikasi = p.getFeeAplikasi();
		this.satuan = p.getSatuan();
		this.bobotSatuan = p.getBobotSatuan();
		this.stok = p.getStok();
		this.sku = p.getSku();
		this.tanggalDibuat = p.getTanggalDibuat();
		this.tanggalDiubah = p.getTanggalDiubah();
		this.hasVarian = p.getHasVarian();
		this.hargaGrosir = p.getHargaGrosir();
		this.varian = p.getVarian().stream().map(VarianProdukMarketplaceDesaDTO::new).toList();
		this.jenisVariasi = p.getJenisVariasi().stream().map(JenisVariasiProdukMarketplaceDesaDTO::new).toList();
	}
}