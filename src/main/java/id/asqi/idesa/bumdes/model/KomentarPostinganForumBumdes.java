package id.asqi.idesa.bumdes.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "komentar_postingan_forum_bumdes")
public class KomentarPostinganForumBumdes {

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "postingan_forum_id", nullable = false)
	@JsonBackReference
	private PostinganForum postinganForum;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_bumdes_id", nullable = false)
	private UserBumdes userBumdes;

	@NotNull
	@Column(name = "isi", nullable = false, columnDefinition = "TEXT")
	private String isi;

	@ManyToOne
	@JoinColumn(name = "komentar_postingan_forum_bumdes_id")
	@JsonBackReference
	private KomentarPostinganForumBumdes parentKomentar;

	@NotNull
	@Column(name = "tanggal_dibuat", nullable = false)
	private LocalDateTime tanggalDibuat;

	@Column(name = "tanggal_diubah")
	private LocalDateTime tanggalDiubah;

	@OneToMany(mappedBy = "parentKomentar")
	@JsonManagedReference
	private List<KomentarPostinganForumBumdes> childKomentar;

	public Boolean isParent(){
		return this.parentKomentar == null;
	}

	public Boolean isChild(){
		return !this.isParent();
	}
}