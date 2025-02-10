package id.asqi.idesa.bumdes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sifat_surat")
@Getter
@Setter
public class SifatSurat {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "sifat")
	private String sifat;

}