package id.asqi.idesa.bumdes.core.http.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

public class TagihanPendudukRequest {

	@Getter
	@Setter
	public static class Create {
		@NotNull
		private Long mappingTagihanId;
		@NotNull
		private Long kategoriTagihanDesaId;

		@NotNull
		private Short tahun;

		@NotNull
		private Short bulan;
		private Integer pemakaianSatuan;
		private BigDecimal totalPotongan;
		private BigDecimal totalTagihan;

//		@NotNull
//		@Range(min = 0, max = 1, message = "0 = Belum lunas; 1 = Lunas")
//		private Short status;
	}

	@Getter
	@Setter
	public static class Update extends IdNumberRequest {
		@NotNull
		private Long kategoriTagihanDesaId;
		private Short tahun;
		private Short bulan;
		private Integer pemakaianSatuan;
		private BigDecimal totalPotongan;
		private BigDecimal totalTagihan;
//		@NotNull
//		@Range(min = 0, max = 1, message = "0 = Belum lunas; 1 = Lunas")
//		private Short status;
	}

	@Getter
	@Setter
	public static class Filter extends SearchPaginationRequest {
		private Long pendudukId;
		private Long mappingTagihanId;
		private Long kategoriTagihanId;
	}
}