package com.example.library_project.entities;

import com.example.library_project.enums.OsebaSpol;
import com.example.library_project.enums.Status;
import javax.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "oseba")
public class Oseba {

    @Id
    @SequenceGenerator(
            name = "oseba_id_sequence",
            sequenceName = "oseba_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oseba_id_sequence")
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "oseba_id", nullable = false)
    private long osebaId;

    @Column(name = "oseba_ime", nullable = false)
    private String osebaIme;

    @Column(name = "oseba_priimek", nullable = false)
    private String osebaPriimek;

    @Column(name = "oseba_datum_rojstva")
    private String osebaDatumRojstva;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "oseba_spol")
    private OsebaSpol osebaSpol;

    @Column(name = "oseba_emso", unique = true, updatable = false)
    private long osebaEmso;

    @Column(name = "oseba_e_mail", unique = true, nullable = false)
    private String osebaEmail;

    @Column(name = "oseba_telefon", nullable = false)
    private String osebaTelefon;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "oseba_status")
    private Status osebaStatus;
    /*
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToStringExclude
    @JoinColumn(name = "oseba_id", referencedColumnName = "oseba_id")
    private List<User> user;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @ToStringExclude
    @JoinColumn(name = "izkaznica_id", referencedColumnName = "izkaznica_id")
    private Izkaznica izkaznica;
}
