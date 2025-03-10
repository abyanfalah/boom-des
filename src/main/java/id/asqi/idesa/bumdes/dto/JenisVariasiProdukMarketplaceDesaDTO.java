package id.asqi.idesa.bumdes.dto;

import id.asqi.idesa.bumdes.model.JenisVariasiProdukMarketplaceDesa;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class JenisVariasiProdukMarketplaceDesaDTO {
	private Long id;
	private String nama;
	private List<OpsiVariasiProdukMarketplaceDesaDTO> opsiVariasi;

	public JenisVariasiProdukMarketplaceDesaDTO(JenisVariasiProdukMarketplaceDesa j) {
		this.id = j.getId();
		this.nama = j.getNama();
		this.opsiVariasi = j.getOpsiVariasi().stream().map(OpsiVariasiProdukMarketplaceDesaDTO::new).toList();
	}
}