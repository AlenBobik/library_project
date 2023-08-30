package com.example.library_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnjiznicaDto {

    @JsonProperty(value = "knjiznica_id", access = JsonProperty.Access.READ_ONLY)
    private long knjiznicaId;

    @Length(min = 1, max = 50, message = "Knjiznica_ime length error.")
    @JsonProperty(value = "knjiznica_ime")
    private String knjiznicaIme;

    @Length(min = 1, max = 50, message = "Knjiznica_naslov length error.")
    @JsonProperty(value = "knjiznica_naslov")
    private String knjiznicaNaslov;

    @Size(min = 4, max = 4, message = "Knjiznica_ime length error.")
    @JsonProperty(value = "knjiznica_postna_stevilka")
    private String knjiznicaPostnaStevilka;

    @JsonProperty(value = "knjiznica_status", access = JsonProperty.Access.READ_ONLY)
    private String knjiznicaStatus;
}
