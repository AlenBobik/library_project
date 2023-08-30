package com.example.library_project.entities;

import javax.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "izposoja")
public class Izposoja {

    @Id
    @SequenceGenerator(
            name = "izposoja_id_sequence",
            sequenceName = "izposoja_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "izposoja_id_sequence")
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "izposoja_id", nullable = false)
    private long izposojaId;

    @Column(name = "izposoja_opomba")
    private String izposojaOpomba;

    @Column(name = "izposoja_datum_izposoje")
    private String izposojaDatumIzposoje;

    @Column(name = "izposoja_datum_poteka")
    private String izposojaDatumPoteka;

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
