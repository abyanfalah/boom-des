package id.asqi.idesa.bumdes.http.request;

import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

public class KategoriTagihanDesaRequest {

	@Getter
	@Setter
	public static class Create {
		@NotNull
		private Long kategoriDasarTagihanDesaId;

		@NotNull
		private String nama;

		@NotNull
		private BigDecimal nominalTagihanBulanan;

		private BigDecimal biayaAdminAplikasi;

		private BigDecimal biayaAdminPengguna;

		private BigDecimal biayaAdminMitraBumdes;

		private BigDecimal denda;

		@NotNull
		@Range(min = 1, max = 2, message = "1 = Rupiah; 2 = Persentase")
		private Short satuanDenda;

		@NotNull
		@Range(min = 1, max = 31)
		private Short tanggalPenagihan;

		@NotNull
		@Range(min = 1, max = 31)
		private Short tanggalJatuhTempo;

		@NotNull
		private Boolean isAktif;
	}

	@Getter
	@Setter
	public static class Update extends IdNumberRequest {
		@NotNull
		private Long kategoriDasarTagihanDesaId;

		@NotNull
		private String nama;

		@NotNull
		private BigDecimal nominalTagihanBulanan;

		private BigDecimal biayaAdminAplikasi;

		private BigDecimal biayaAdminPengguna;

		private BigDecimal biayaAdminMitraBumdes;

		private BigDecimal denda;

		@NotNull
		@Range(min = 1, max = 2, message = "1 = Rupiah; 2 = Persentase")
		private Short satuanDenda;

		@NotNull
		@Range(min = 1, max = 31)
		private Short tanggalPenagihan;

		@NotNull
		@Range(min = 1, max = 31)
		private Short tanggalJatuhTempo;

		@NotNull
		private Boolean isAktif;
	}
}