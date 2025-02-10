package id.asqi.idesa.bumdes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kelompok_pangan")
public class KelompokPangan {
	@Id
	@NotNull
	@Column(name = "id", nullable = false)
	private Long id;

	@Size(max = 128)
	@NotNull
	@Column(name = "nama", nullable = false, length = 128)
	private String nama;

	@NotNull
	@Column(name = "deskripsi", nullable = false, length = Integer.MAX_VALUE)
	private String deskripsi;

}