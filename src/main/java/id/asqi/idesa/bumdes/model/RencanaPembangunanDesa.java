package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "rencana_pembangunan_desa")
public class RencanaPembangunanDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 5)
    @Column(name = "tahun_anggaran", length = 5)
    private String tahunAnggaran;

    @Size(max = 255)
    @Column(name = "nama")
    private String nama;

    @Size(max = 255)
    @Column(name = "lokasi")
    private String lokasi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sumber_biaya_id")
    private SumberBiaya sumberBiaya;

    @Column(name = "jumlah_biaya")
    private Long jumlahBiaya;

    @Size(max = 100)
    @Column(name = "pelaksana", length = 100)
    private String pelaksana;

    @Size(max = 255)
    @Column(name = "manfaat")
    private String manfaat;

    @Column(name = "keterangan")
    private Integer keterangan;

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

    @Column(name = "tanggal_diubah")
    private Instant tanggalDiubah;

}