package id.asqi.idesa.bumdes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "penduduk_penerima_bantuan")
public class PendudukPenerimaBantuan {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "penduduk_id", nullable = false)
    private Penduduk penduduk;

    @NotNull
    @Column(name = "penerimaan_bantuan_id", nullable = false)
    private Long penerimaanBantuanId;

}