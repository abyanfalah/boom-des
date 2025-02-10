package id.asqi.idesa.bumdes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "brute_force")
public class BruteForce {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "key")
    private String key;

    @Size(max = 255)
    @Column(name = "ipaddress")
    private String ipaddress;

    @Column(name = "count")
    private Integer count;

    @Column(name = "date")
    private LocalDate date;

}