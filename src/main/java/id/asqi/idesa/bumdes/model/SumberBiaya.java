package id.asqi.idesa.bumdes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sumber_biaya")
@Getter
@Setter
public class SumberBiaya {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "tipe")
	private Integer tipe;

	@Column(name = "nama")
	private String nama;

}