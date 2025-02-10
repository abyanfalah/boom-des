package id.asqi.idesa.bumdes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "pengaturan_jam")
public class PengaturanJam {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "jam_masuk")
    private LocalTime jamMasuk;

    @Column(name = "jam_pulang")
    private LocalTime jamPulang;

    @Column(name = "jam_lembur")
    private LocalTime jamLembur;

}