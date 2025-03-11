package id.asqi.idesa.bumdes.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "kategori_postingan_forum_bumdes")
public class KategoriPostinganForumBumdes {

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Size(max = 128)
	@NotNull
	@Column(name = "nama", nullable = false, length = 128)
	private String nama;

	@Column(name = "tanggal_dihapus")
	private LocalDateTime tanggalDihapus;
}