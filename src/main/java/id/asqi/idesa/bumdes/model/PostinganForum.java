package id.asqi.idesa.bumdes.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "postingan_forum")
public class PostinganForum {

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@NotNull
	@Column(name = "judul", nullable = false)
	private String judul;

	@NotNull
	@Column(name = "isi", nullable = false, columnDefinition = "TEXT")
	private String isi;

	@ManyToOne
	@JoinColumn(name = "kategori_postingan_forum_bumdes_id")
	private KategoriPostinganForumBumdes kategori;

	@OneToMany(mappedBy = "postinganForum")
	private List<GambarPostinganForum> gambar;

	@NotNull
	@Column(name = "tanggal_dibuat", nullable = false)
	private LocalDateTime tanggalDibuat;

	@Column(name = "tanggal_diubah")
	private LocalDateTime tanggalDiubah;

	@Column(name = "tanggal_dihapus")
	private LocalDateTime tanggalDihapus;
}