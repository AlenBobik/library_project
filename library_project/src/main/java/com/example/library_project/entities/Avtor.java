package com.example.library_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "avtor")
public class Avtor {

    @Id
    @SequenceGenerator(
            name = "avtor_id_sequence",
            sequenceName = "avtor_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "avtor_id_sequence")
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "avtor_id", nullable = false)
    private long avtorId;

    @Column(name = "avtor_ime_priimek")
    private String avtorImePriimek;

    @JsonIgnore
    @ManyToMany(mappedBy = "avtor")
    private List<KnjigaIzvod> knjigaIzvod;
}
