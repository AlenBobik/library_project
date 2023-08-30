package com.example.library_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IzkaznicaDto {

    @Length(min = 1, max = 20, message = "Izkaznica_oznaka length error.")
    @JsonProperty(value = "izkaznica_oznaka", access = JsonProperty.Access.READ_ONLY)
    private String izkaznicaOznaka;

    @Type(type = "date")
    @JsonProperty(value = "izkaznica_datum_izdaje")
    private String izkaznicaDatumIzdaje;

    @Type(type = "date")
    @JsonProperty(value = "izkaznica_datum_poteka")
    private String izkaznicaDatumpoteka;

    @JsonProperty(value = "izkaznica_status", access = JsonProperty.Access.READ_ONLY)
    private String izkaznicaStatus;
}
