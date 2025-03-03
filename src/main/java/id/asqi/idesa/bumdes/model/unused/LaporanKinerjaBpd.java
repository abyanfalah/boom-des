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
@Table(name = "laporan_kinerja_bpd")
public class LaporanKinerjaBpd {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tanggal")
    private LocalDate tanggal;

    @Size(max = 5)
    @Column(name = "tahun_anggaran", length = 5)
    private String tahunAnggaran;

    @Size(max = 100)
    @Column(name = "nama_petugas", length = 100)
    private String namaPetugas;

    @Column(name = "jabatan_id")
    private Long jabatanId;

    @Size(max = 255)
    @Column(name = "alamat")
    private String alamat;

    @Size(max = 255)
    @Column(name = "dasar_hukum")
    private String dasarHukum;

    @Column(name = "mulai_pelaksanaan_tugas")
    private LocalDate mulaiPelaksanaanTugas;

    @Column(name = "selesai_pelaksanaan_tugas")
    private LocalDate selesaiPelaksanaanTugas;

    @Size(max = 255)
    @Column(name = "prestasi")
    private String prestasi;

    @Size(max = 255)
    @Column(name = "saran_bpd")
    private String saranBpd;

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