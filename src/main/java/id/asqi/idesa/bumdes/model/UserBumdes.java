package id.asqi.idesa.bumdes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_bumdes")
public class UserBumdes {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "alamat")
	private String alamat;

	@Column(name = "jabatan_id")
	private String jabatanId;

	@Column(name = "tanggal_dibuat")
	private String tanggalDibuat;

	@Column(name = "tanggal_diubah")
	private String tanggalDiubah;

	@Column(name = "foto_profil")
	private String fotoProfil;
}