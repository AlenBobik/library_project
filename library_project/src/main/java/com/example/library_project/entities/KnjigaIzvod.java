package com.example.library_project.entities;

import java.util.List;
import javax.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "knjiga_izvod")
public class KnjigaIzvod {

    @Id
    @SequenceGenerator(
            name = "knjiga_izvod_id_sequence",
            sequenceName = "knjiga_izvod_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "knjiga_izvod_id_sequence")
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "knjiga_izvod_id", nullable = false)
    private long knjigaIzvodId;

    @Column(name = "knjiga_izvod_naslov")
    private String knjigaIzvodNaslov;

    @Column(name = "knjiga_izvod_isbn", unique = true, nullable = false)
    private String knjigaIzvodIsbn;

    @Column(name = "knjiga_izvod_datum_izdaje")
    private String knjigaIzvodDatumIzdaje;

    @Column(name = "knjiga_izvod_image_path")
    private String knjigaIzvodImagePath;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Knjiga> knjiga;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Izposoja> izposoja;

    @ManyToMany
    @JoinTable(
            name = "knjiga_izvod_avtor",
            joinColumns = @JoinColumn(name = "knjiga_izvod_id"),
            inverseJoinColumns = @JoinColumn(name = "avtor_id"))
    private List<Avtor> avtor;

    @ManyToMany
    @JoinTable(
            name = "knjizna_polica_avtor",
            joinColumns = @JoinColumn(name = "knjiga_izvod_id"),
            inverseJoinColumns = @JoinColumn(name = "knjizna_polica_id"))
    private List<Avtor> knjiznaPolica;
}
