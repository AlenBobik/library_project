package com.example.library_project.entities;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "obvestilo")
public class Obvestilo {

    @Id
    @SequenceGenerator(
            name = "obvestilo_id_sequence",
            sequenceName = "obvestilo_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obvestilo_id_sequence")
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "obvestilo_id", nullable = false)
    private long obvestiloId;

    @Column(name = "obvestilo_ime", nullable = false)
    private String obvestiloIme;

    @Column(name = "obvestilo_opis")
    private String obvestiloOpis;

    @Column(name = "obvestilo_datum")
    private Date obvestiloDatum;
    /*
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToStringExclude
    @JoinColumn(name = "obvestilo_id", referencedColumnName = "obvestilo_id")
    private List<User> user;*/
}
