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
@Table(name = "kerjasama_desa")
public class KerjasamaDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tanggal_kesepakatan")
    private LocalDate tanggalKesepakatan;

    @Column(name = "jenis_kerjasama_id")
    private Long jenisKerjasamaId;

    @Size(max = 255)
    @Column(name = "bidang")
    private String bidang;

    @Size(max = 255)
    @Column(name = "nama_kegiatan")
    private String namaKegiatan;

    @Size(max = 255)
    @Column(name = "pihak_bekerjasama")
    private String pihakBekerjasama;

    @Size(max = 255)
    @Column(name = "pokok_keputusan")
    private String pokokKeputusan;

    @Size(max = 255)
    @Column(name = "keterangan")
    private String keterangan;

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