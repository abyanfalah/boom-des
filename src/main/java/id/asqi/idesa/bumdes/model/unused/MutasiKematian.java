package id.asqi.idesa.bumdes.model.unused;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "mutasi_kematian")
public class MutasiKematian {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "penduduk_id")
    private Long pendudukId;

    @Column(name = "tanggal_kematian")
    private LocalDate tanggalKematian;

    @Column(name = "tanggal_pelaporan_kematian")
    private LocalDate tanggalPelaporanKematian;

    @Size(max = 255)
    @Column(name = "nama_pelapor")
    private String namaPelapor;

    @Size(max = 255)
    @Column(name = "foto_surat")
    private String fotoSurat;

    @Size(max = 255)
    @Column(name = "dibuat_oleh")
    private String dibuatOleh;

    @Column(name = "tanggal_ditambah")
    private Instant tanggalDitambah;

}