package id.asqi.idesa.bumdes.model;

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
@Table(name = "pengumuman")
public class Pengumuman {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "foto")
    private String foto;

    @Size(max = 255)
    @Column(name = "judul")
    private String judul;

    @Column(name = "tanggal")
    private LocalDate tanggal;

    @Column(name = "uraian", length = Integer.MAX_VALUE)
    private String uraian;

    @Column(name = "peruntukan")
    private Integer peruntukan;

    @Column(name = "tanggal_ditambah")
    private Instant tanggalDitambah;

    @Column(name = "alamat_desa_id")
    private Long alamatDesaId;

    @Column(name = "alamat_kecamatan_id")
    private Long alamatKecamatanId;

    @Column(name = "alamat_kabupaten_id")
    private Long alamatKabupatenId;

    @Column(name = "alamat_provinsi_id")
    private Long alamatProvinsiId;

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}