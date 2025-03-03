package id.asqi.idesa.bumdes.model.unused;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipe_keputusan")
@Getter
@Setter
public class TipeKeputusan {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "nama")
	private String nama;

}