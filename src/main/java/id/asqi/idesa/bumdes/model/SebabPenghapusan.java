package id.asqi.idesa.bumdes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sebab_penghapusan")
@Getter
@Setter
public class SebabPenghapusan {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "nama")
	private String nama;

}