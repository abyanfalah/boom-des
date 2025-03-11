package id.asqi.idesa.bumdes.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gambar_postingan_forum")
public class GambarPostinganForum {
	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "postingan_forum_id")
	private PostinganForum postinganForum;

	@Column(name = "url")
	private String url;
}