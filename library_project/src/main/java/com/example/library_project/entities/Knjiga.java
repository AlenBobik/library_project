package com.example.library_project.entities;

import com.example.library_project.enums.KnjigaStatus;
import com.example.library_project.enums.KnjigaVrstaKnjige;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "knjiga")
public class Knjiga {
    @Id
    @SequenceGenerator(
            name = "knjiga_id_sequence",
            sequenceName = "knjiga_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "knjiga_id_sequence")
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "knjiga_id", nullable = false)
    private long knjigaId;

    @Column(name = "knjiga_uuid", nullable = false, updatable = false, unique = true)
    private UUID knjigaUuid;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "knjiga_status")
    private KnjigaStatus knjigaStatus;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "knjiga_vrsta_knjige")
    private KnjigaVrstaKnjige knjigaVrstaKnjige;

    @Column(name = "knjiga_jezik")
    private String knjigaJezik;

    @Column(name = "knjiga_izdaja")
    private String knjigaIzdaja;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToStringExclude
    @JoinColumn(
            name = "knjiga_izvod_id",
            referencedColumnName = "knjiga_izvod_id",
            nullable = false)
    private KnjigaIzvod knjigaIzvod;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToStringExclude
    @JoinColumn(name = "knjiznica_id", referencedColumnName = "knjiznica_id", nullable = false)
    private Knjiznica knjiznica;
}
