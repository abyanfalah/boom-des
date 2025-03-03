package id.asqi.idesa.bumdes.http.request;

import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public class ProdukGrosirRequest {
	@Getter
	@Setter
	public static class Create {

		@NotBlank
		private String nama;

		@NotNull
		private Long kategoriProdukGrosirId;

		@NotNull(message = "Kondisi tidak boleh kosong. 1 = Baru, 2 = Bekas")
		@Range(min = 1, max = 2, message = "Kondisi harus 1 = Baru atau 2 = Bekas")
		@NotNull
		private Short kondisi;

		private Integer stok;

		@NotNull
		private Long satuanId;

		@NotNull
		private Integer beratSatuanKg;

		@NotNull
		private BigDecimal harga;

		private BigDecimal hargaDiskon;

		@NotNull
		private Boolean isAktif;

		@NotBlank
		private String deskripsi;

		private List<MultipartFile> gambar;
	}

	@Getter
	@Setter
	public static class Update extends IdNumberRequest {

		@NotBlank
		private String nama;

		@NotNull
		private Long kategoriProdukGrosirId;

		@NotNull(message = "Kondisi tidak boleh kosong. 1 = Baru, 2 = Bekas")
		@Range(min = 1, max = 2, message = "Kondisi harus 1 = Baru atau 2 = Bekas")
		@NotNull
		private Short kondisi;

		private Integer stok;

		@NotNull
		private Long satuanId;

		@NotNull
		private Integer beratSatuanKg;

		@NotNull
		private BigDecimal harga;

		private BigDecimal hargaDiskon;

		@NotNull
		private Boolean isAktif;

		@NotBlank
		private String deskripsi;

		private List<MultipartFile> gambar;
	}

}