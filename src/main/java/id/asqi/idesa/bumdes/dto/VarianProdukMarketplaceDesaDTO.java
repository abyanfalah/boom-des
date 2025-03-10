package id.asqi.idesa.bumdes.dto;

import id.asqi.idesa.bumdes.model.Satuan;
import id.asqi.idesa.bumdes.model.VarianProdukMarketplaceDesa;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class VarianProdukMarketplaceDesaDTO {
	private Long id;
	private BigDecimal hargaJualUtama;
	private BigDecimal hargaModal;
	private Integer bobotSatuan;
	private Satuan satuan;
	private Integer stok;
	private String sku;
	private Boolean isAktif;
	private Boolean isUtama;
	private LocalDateTime tanggalDibuat;
	private LocalDateTime tanggalDiubah;
	private List<MappingOpsiVarianProdukDTO> mappingOpsiVariasi;

	public VarianProdukMarketplaceDesaDTO(VarianProdukMarketplaceDesa v){
		this.id = v.getId();
		this.hargaJualUtama = v.getHargaJualUtama();
		this.hargaModal = v.getHargaModal();
		this.bobotSatuan = v.getBobotSatuan();
		this.satuan = v.getSatuan();
		this.stok = v.getStok();
		this.sku = v.getSku();
		this.isAktif = v.getIsAktif();
		this.isUtama = v.getIsUtama();
		this.tanggalDibuat = v.getTanggalDibuat();
		this.tanggalDiubah = v.getTanggalDiubah();
		this.mappingOpsiVariasi = v.getOpsiVariasi().stream().map(MappingOpsiVarianProdukDTO::new).toList();

	}
}