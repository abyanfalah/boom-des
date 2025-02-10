package id.asqi.idesa.bumdes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "peta_desa")
public class PetaDesa {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "koordinat", length = 100)
    private String koordinat;

    @Size(max = 100)
    @Column(name = "batas_utara", length = 100)
    private String batasUtara;

    @Size(max = 100)
    @Column(name = "batas_barat", length = 100)
    private String batasBarat;

    @Size(max = 100)
    @Column(name = "batas_timur", length = 100)
    private String batasTimur;

    @Size(max = 100)
    @Column(name = "batas_selatan", length = 100)
    private String batasSelatan;

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