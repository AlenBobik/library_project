package com.example.library_project.entities;

import com.example.library_project.enums.Status;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "izkaznica")
public class Izkaznica {
    @Id
    @SequenceGenerator(
            name = "izkaznica_id_sequence",
            sequenceName = "izkaznica_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "izkaznica_id_sequence")
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "izkaznica_id", nullable = false)
    private long izkaznicaId;

    @Column(name = "izkaznica_oznaka", unique = true, nullable = false)
    private String izkaznicaOznaka;

    @Column(name = "izkaznica_datum_izdaje")
    private String izkaznicaDatumIzdaje;

    @Column(name = "izkaznica_datum_poteka")
    private String izkaznicaDatumpoteka;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "izkaznica_status")
    private Status izkaznicaStatus;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToStringExclude
    @JoinColumn(name = "izkaznica_id", referencedColumnName = "izkaznica_id")
    private List<Oseba> oseba;
}
