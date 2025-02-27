package id.asqi.idesa.bumdes.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "rekening_user_bumdes")
public class RekeningUserBumdes {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 32)
    @NotNull
    @Column(name = "nama_bank", nullable = false, length = 32)
    private String namaBank;

    @NotNull
    @Column(name = "atas_nama", nullable = false, length = Integer.MAX_VALUE)
    private String atasNama;

    @Size(max = 32)
    @NotNull
    @Column(name = "nomor_rekening", nullable = false, length = 32)
    private String nomorRekening;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_bumdes_id", nullable = false)
    private UserBumdes userBumdes;

    @NotNull
    @Column(name = "is_rekening_utama", nullable = false)
    private Boolean isRekeningUtama = false;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "tanggal_dibuat", nullable = false)
    private LocalDateTime tanggalDibuat;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "tanggal_diubah")
    private LocalDateTime tanggalDiubah;

}