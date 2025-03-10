package id.asqi.idesa.bumdes.http.request;


import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.util.List;

public class ProdukMarketplaceDesaRequest {

	@Getter
	@Setter
	public static class Create{
		@NotBlank
		private String nama;

		@NotNull
		private Long kategoriProdukMarketplaceDesaId;

		@NotNull(message = "Kondisi tidak boleh kosong. 1 = Baru, 2 = Bekas")
		@Range(min = 1, max = 2, message = "Kondisi harus 1 = Baru atau 2 = Bekas")
		@NotNull
		private Short kondisi;

		@NotBlank
		private String deskripsi;

		@NotNull
		private Boolean isAktif;

		@NotNull
		private Long satuanId;

		@NotNull @Min(value = 0, message = "Minimal 0")
		private Integer bobotSatuan;

		private String sku;

		@NotNull @Min(0)
		private Integer stok;

		@NotNull
		private Boolean hasVarian;

		@NotNull
		private BigDecimal hargaJualUtama;

		@NotNull
		private BigDecimal hargaModal;

		@NotNull
		private BigDecimal feeAplikasi;

		@NotNull(message = "Pilihan harga jual tidak boleh kosong. 1 = default, 2 = harga grosir, 3 = harga diskon")
		@Range(min = 1, max = 3, message = "1 = default, 2 = harga grosir, 3 = harga diskon")
		private Integer pilihanHargaJual;

		private List<HargaJualGrosir> hargaJualGrosir;

		private BigDecimal potonganHargaDiskon;

		private List<JenisVarian> jenisVarian;

		private List<Varian> varian;


		@Getter
		@Setter
		public static class HargaJualGrosir{
			@NotNull
			private Integer minimalPembelian;

			@NotNull
			private BigDecimal hargaJualGrosir;
		}


		@Getter @Setter
		public static class JenisVarian{
			@NotBlank
			private String nama;

			@NotEmpty
			private List<OpsiVarian> opsiVarian;
		}

		@Getter @Setter
		public static class OpsiVarian{
			@NotBlank
			private String nama;
		}

		@Getter @Setter
		public static class Varian{
			@NotNull
			private BigDecimal hargaJualUtama;

			@NotNull
			private BigDecimal hargaModal;

			@NotNull
			private Long satuanId;

			@NotNull @Min(value = 0, message = "Minimal 0")
			private Integer bobotSatuan;

			private String sku;

			@NotNull @Min(0)
			private Integer stok;

			@NotNull
			private Boolean isAktif;

			@NotEmpty
			private List<JenisVariasiWithOpsi> opsiVarian;

			@NotNull
			private Boolean isUtama;

			public Boolean getIsUtama() {
				return isUtama != null && isUtama;
			}
		}


		@Getter @Setter
		public static class JenisVariasiWithOpsi {
			@NotBlank
			private String namaJenisVariasi;

			@NotBlank
			private String namaOpsiVariasi;
		}
	}

	@Getter
	@Setter
	public static class Update extends IdNumberRequest{
		@NotBlank
		private String nama;

		@NotNull
		private Long kategoriProdukMarketplaceDesaId;

		@NotNull(message = "Kondisi tidak boleh kosong. 1 = Baru, 2 = Bekas")
		@Range(min = 1, max = 2, message = "Kondisi harus 1 = Baru atau 2 = Bekas")
		@NotNull
		private Short kondisi;

		@NotBlank
		private String deskripsi;

		@NotNull
		private Boolean isAktif;

		@NotNull
		private Long satuanId;

		@NotNull @Min(value = 0, message = "Minimal 0")
		private Integer bobotSatuan;

		private String sku;

		@NotNull @Min(0)
		private Integer stok;

		@NotNull
		private Boolean hasVarian;

		@NotNull
		private BigDecimal hargaJualUtama;

		@NotNull
		private BigDecimal hargaModal;

		@NotNull
		private BigDecimal feeAplikasi;

		@NotNull(message = "Pilihan harga jual tidak boleh kosong. 1 = default, 2 = harga grosir, 3 = harga diskon")
		@Range(min = 1, max = 3, message = "1 = default, 2 = harga grosir, 3 = harga diskon")
		private Integer pilihanHargaJual;

		private List<HargaJualGrosir> hargaJualGrosir;

		private BigDecimal potonganHargaDiskon;

		private List<JenisVarian> jenisVarian;

		private List<Varian> varian;


		@Getter
		@Setter
		public static class HargaJualGrosir extends IdNumberRequest{
			@NotNull
			private Integer minimalPembelian;

			@NotNull
			private BigDecimal hargaJualGrosir;
		}


		@Getter @Setter
		public static class JenisVarian extends IdNumberRequest{
			@NotBlank
			private String nama;

			@NotEmpty
			private List<OpsiVarian> opsiVarian;
		}

		@Getter @Setter
		public static class OpsiVarian extends IdNumberRequest{
			@NotBlank
			private String nama;
		}

		@Getter @Setter
		public static class Varian extends IdNumberRequest{
			@NotNull
			private BigDecimal hargaJualUtama;

			@NotNull
			private BigDecimal hargaModal;

			@NotNull
			private Long satuanId;

			@NotNull @Min(value = 0, message = "Minimal 0")
			private Integer bobotSatuan;

			private String sku;

			@NotNull @Min(0)
			private Integer stok;

			@NotNull
			private Boolean isAktif;

			@NotEmpty
			private List<JenisVariasiWithOpsi> opsiVarian;

			@NotNull
			private Boolean isUtama;

			public Boolean getIsUtama() {
				return isUtama != null && isUtama;
			}
		}


		@Getter @Setter
		public static class JenisVariasiWithOpsi extends IdNumberRequest{
			@NotBlank
			private String namaJenisVariasi;

			@NotBlank
			private String namaOpsiVariasi;
		}
	}
}