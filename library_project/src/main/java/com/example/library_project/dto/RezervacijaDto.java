package com.example.library_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RezervacijaDto {

    @JsonProperty(value = "rezervacija_id", access = JsonProperty.Access.READ_ONLY)
    private long rezervacijaId;

    @NotBlank
    @JsonProperty(value = "rezervacija_opomba")
    private String rezervacijaOpomba;

    @JsonProperty(value = "rezervacija_datum_rezervacije", access = JsonProperty.Access.READ_ONLY)
    private LocalDate rezervacijaDatumRezervacije;

    @JsonProperty(value = "rezervacija_datum_poteka", access = JsonProperty.Access.READ_ONLY)
    private LocalDate rezervacijaDatumPoteka;

    @JsonProperty(value = "user_username")
    private String userUsername;

    @JsonProperty(value = "knjiga_izvod_isbn")
    private String knjigaIzvodIsbn;
}
