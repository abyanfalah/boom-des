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
@Table(name = "buku_kegiatan_bpd")
public class BukuKegiatanBpd {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tanggal")
    private LocalDate tanggal;

    @Column(name = "jenis_id")
    private Long jenisId;

    @Size(max = 100)
    @Column(name = "pelaksana", length = 100)
    private String pelaksana;

    @Size(max = 255)
    @Column(name = "agenda")
    private String agenda;

    @Size(max = 255)
    @Column(name = "tindak_lanjut")
    private String tindakLanjut;

    @Size(max = 255)
    @Column(name = "hasil_singkat_kegiatan")
    private String hasilSingkatKegiatan;

    @Size(max = 255)
    @Column(name = "foto")
    private String foto;

    @Column(name = "alamat_desa_id")
    private Long alamatDesaId;

    @Column(name = "alamat_kecamatan_id")
    private Long alamatKecamatanId;

    @Column(name = "alamat_kabupaten_id")
    private Long alamatKabupatenId;

    @Column(name = "alamat_provinsi_id")
    private Long alamatProvinsiId;

    @Column(name = "tanggal_ditambah")
    private Instant tanggalDitambah;

}