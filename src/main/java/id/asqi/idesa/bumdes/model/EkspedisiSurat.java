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
@Table(name = "ekspedisi_surat")
public class EkspedisiSurat {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "surat_keluar_id")
    private Long suratKeluarId;

    @Column(name = "tanggal_pengiriman")
    private LocalDate tanggalPengiriman;

    @Size(max = 255)
    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "tanggal_ditambah")
    private Instant tanggalDitambah;

}