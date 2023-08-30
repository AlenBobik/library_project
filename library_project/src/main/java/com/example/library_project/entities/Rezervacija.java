package com.example.library_project.entities;

import javax.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rezervacija")
public class Rezervacija {

    @Id
    @SequenceGenerator(
            name = "rezervacija_id_sequence",
            sequenceName = "rezervacija_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rezervacija_id_sequence")
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "rezervacija_id", nullable = false)
    private long rezervacijaId;

    @Column(name = "rezervacija_opomba")
    private String rezervacijaOpomba;

    @Column(name = "rezervacija_datum_rezervacije")
    private String rezervacijaDatumRezervacije;

    @Column(name = "rezervacija_datum_poteka")
    private String rezervacijaDatumPoteka;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToStringExclude
    @JoinColumn(
            name = "knjiga_izvod_id",
            referencedColumnName = "knjiga_izvod_id",
            nullable = false)
    private KnjigaIzvod knjigaIzvod;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToStringExclude
    @JoinColumn(name = "users_id", referencedColumnName = "users_id", nullable = false)
    private User user;
}
