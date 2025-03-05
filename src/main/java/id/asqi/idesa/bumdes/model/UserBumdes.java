package id.asqi.idesa.bumdes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_bumdes")
@Getter
@Setter
@NoArgsConstructor
public class UserBumdes{

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String username;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	@Column(name = "alamat")
	private String alamat;

	@ManyToOne
	@JoinColumn(name = "jabatan_id")
	private Jabatan jabatan;

	@ManyToOne
	@JoinColumn(name = "alamat_desa_id")
	private AlamatDesa alamatDesa;

	@Column(name = "tanggal_dibuat")
	private LocalDateTime tanggalDibuat;

	@Column(name = "tanggal_diubah")
	private LocalDateTime tanggalDiubah;

	@Column(name = "foto_profil")
	private String fotoProfil;
}