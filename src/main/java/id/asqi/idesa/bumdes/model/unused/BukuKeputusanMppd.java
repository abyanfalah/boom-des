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
@Table(name = "buku_keputusan_mppd")
public class BukuKeputusanMppd {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tanggal_kegiatan")
    private LocalDate tanggalKegiatan;

    @Column(name = "untuk_tahun_anggaran")
    private LocalDate untukTahunAnggaran;

    @Column(name = "jenis_musyawarah_id")
    private Long jenisMusyawarahId;

    @Size(max = 255)
    @Column(name = "agenda_musyawarah")
    private String agendaMusyawarah;

    @Size(max = 255)
    @Column(name = "bidang_penyelenggalaraan")
    private String bidangPenyelenggalaraan;

    @Size(max = 255)
    @Column(name = "bidang_pelaksanaan")
    private String bidangPelaksanaan;

    @Size(max = 255)
    @Column(name = "bidang_pembinaan")
    private String bidangPembinaan;

    @Size(max = 255)
    @Column(name = "bidang_pemberdayaan")
    private String bidangPemberdayaan;

    @Size(max = 255)
    @Column(name = "keterangan")
    private String keterangan;

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