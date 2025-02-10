package id.asqi.idesa.bumdes.model;

import id.asqi.idesa.bumdes.model.UserBumdes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "voucher_bantuan")
@Getter
@Setter
public class VoucherBantuan {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "nama")
	private String nama;

	@Column(name = "deskripsi")
	private String deskripsi;

	@Column(name = "jumlah_penerima")
	private Integer jumlahPenerima;

	@Column(name = "tanggal_pengambilan")
	@Temporal(TemporalType.DATE)
	private java.util.Date tanggalPengambilan;

	@ManyToOne
	@JoinColumn(name = "user_bumdes_id")
	private UserBumdes userBumdes;

	@Column(name = "tanggal_dibuat")
	private LocalDateTime tanggalDibuat;

	@Column(name = "tanggal_diubah")
	private java.util.Date tanggalDiubah;

}