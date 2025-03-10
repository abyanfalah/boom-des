package id.asqi.idesa.bumdes.dto;

import id.asqi.idesa.bumdes.model.JenisVariasiProdukMarketplaceDesa;
import id.asqi.idesa.bumdes.model.OpsiVariasiProdukMarketplaceDesa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MappingOpsiVarianProdukDTO {
	private JenisVariasiDTO jenisVariasi;
	private OpsiVariasiDTO opsiVariasi;

	public MappingOpsiVarianProdukDTO(OpsiVariasiProdukMarketplaceDesa opsi) {
		this.jenisVariasi = new JenisVariasiDTO(opsi.getJenisVariasiProdukMarketplaceDesa());
		this.opsiVariasi = new OpsiVariasiDTO(opsi);
	}

	@Getter
	@Setter
	public static class JenisVariasiDTO {
		private Long id;
		private String nama;

		public JenisVariasiDTO(JenisVariasiProdukMarketplaceDesa j) {
			this.id = j.getId();
			this.nama = j.getNama();
		}
	}

	@Getter
	@Setter
	public static class OpsiVariasiDTO {
		private Long id;
		private String nama;

		public OpsiVariasiDTO(OpsiVariasiProdukMarketplaceDesa o) {
			this.id = o.getId();
			this.nama = o.getNama();
		}
	}
}