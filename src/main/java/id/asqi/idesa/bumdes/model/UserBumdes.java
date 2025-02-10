package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// User Bumdes
@Entity
@Table(name = "user_bumdes")
@Getter
@Setter
public class UserBumdes {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password") // Consider encrypting password!
	private String password;

	@Column(name = "alamat")
	private String alamat;

	@ManyToOne
	@JoinColumn(name = "jabatan_id")
	private Jabatan jabatan;

	@Column(name = "tanggal_dibuat")
	private LocalDateTime tanggalDibuat;

	@Column(name = "tanggal_diubah")
	private LocalDateTime tanggalDiubah;

	@Column(name = "foto_profil")
	private String fotoProfil;

}