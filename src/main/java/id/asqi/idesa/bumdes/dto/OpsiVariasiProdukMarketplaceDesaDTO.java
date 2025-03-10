package id.asqi.idesa.bumdes.dto;

import id.asqi.idesa.bumdes.model.OpsiVariasiProdukMarketplaceDesa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpsiVariasiProdukMarketplaceDesaDTO {
	private Long id;
	private String nama;

	public OpsiVariasiProdukMarketplaceDesaDTO(OpsiVariasiProdukMarketplaceDesa o) {
		this.id = o.getId();
		this.nama = o.getNama();
	}
}