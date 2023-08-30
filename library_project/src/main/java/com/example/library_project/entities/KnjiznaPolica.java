package com.example.library_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "knjizna_polica")
public class KnjiznaPolica {

    @Id
    @SequenceGenerator(
            name = "knjizna_polica_id_sequence",
            sequenceName = "knjizna_polica_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "knjizna_polica_id_sequence")
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "knjizna_polica_id", nullable = false)
    private long knjiznaPolicaId;

    @Column(name = "knjizna_polica_oznaka", unique = true, nullable = false)
    private String knjiznaPolicaOznaka;

    @JsonIgnore
    @ManyToMany(mappedBy = "knjiznaPolica")
    private List<KnjigaIzvod> knjigaIzvod;
}
