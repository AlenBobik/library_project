package com.example.library_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KnjigaIzvodDto {

    @NotBlank(message = "Naslov must not be empty.")
    @JsonProperty(value = "knjiga_izvod_naslov")
    private String knjigaIzvodNaslov;

    @NotBlank(message = "ISBN must not be empty.")
    @JsonProperty(value = "knjiga_izvod_isbn")
    private String knjigaIzvodIsbn;

    @NotBlank(message = "Datum izdaje must not be empty.")
    @JsonProperty(value = "knjiga_izvod_datum_izdaje")
    private String knjigaIzvodDatumIzdaje;

    @NotBlank(message = "Datum izdaje must not be empty.")
    @JsonProperty(value = "knjiga_izvod_image_path")
    private String knjigaIzvodImagePath;
}
