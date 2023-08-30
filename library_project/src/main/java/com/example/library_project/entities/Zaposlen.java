package com.example.library_project.entities;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "zaposlen")
public class Zaposlen {

    @Id
    @SequenceGenerator(
            name = "zaposlen_id_sequence",
            sequenceName = "zaposlen_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zaposlen_id_sequence")
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "zaposlen_id", nullable = false)
    private long zaposlenId;

    @Column(name = "zaposlen_oznaka_pogodbe", nullable = false, unique = true, updatable = false)
    private String zaposlenOznakaPogodbe;

    @Column(name = "zaposlen_od", nullable = false)
    private Date zaposlenOd;

    @Column(name = "zaposlen_do", nullable = false)
    private Date zaposlenDo;
    /*
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToStringExclude
    @JoinColumn(name = "zaposlen_id", referencedColumnName = "zaposlen_id")
    private List<User> user;*/
}
