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
public class IzposojaDto {

    @JsonProperty(value = "izposoja_id", access = JsonProperty.Access.READ_ONLY)
    private long izposojaId;

    @NotBlank
    @JsonProperty(value = "izposoja_opomba")
    private String izposojaOpomba;

    @JsonProperty(value = "izposoja_datum_izposoje", access = JsonProperty.Access.READ_ONLY)
    private LocalDate izposojaDatumIzposoje;

    @JsonProperty(value = "izposoja_datum_poteka", access = JsonProperty.Access.READ_ONLY)
    private LocalDate izposojaDatumPoteka;

    @JsonProperty(value = "user_username")
    private String userUsername;

    @JsonProperty(value = "knjiga_izvod_isbn")
    private String knjigaIzvodIsbn;
}
