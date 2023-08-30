package com.example.library_project.entities;

import com.example.library_project.enums.KnjiznicaStatus;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "knjiznica")
public class Knjiznica {

    @Id
    @SequenceGenerator(
            name = "knjiznica_id_sequence",
            sequenceName = "knjiznica_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "knjiznica_id_sequence")
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "knjiznica_id", nullable = false)
    private long knjiznicaId;

    @Column(name = "knjiznica_ime", nullable = false)
    private String knjiznicaIme;

    @Column(name = "knjiznica_naslov")
    private String knjiznicaNaslov;

    @Column(name = "knjiznica_postna_stevilka")
    private String knjiznicaPostnaStevilka;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "knjiznica_status")
    private KnjiznicaStatus knjiznicaStatus;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToStringExclude
    @JoinColumn(name = "knjiznica_id", referencedColumnName = "knjiznica_id")
    private List<Knjiga> knjiga;
}
