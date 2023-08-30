package com.example.library_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnjiznaPolicaDto {

    @JsonProperty(value = "knjizna_polica_id", access = JsonProperty.Access.READ_ONLY)
    private long knjiznaPolicaId;

    @NotEmpty(message = "knjizna_polica_oznaka cannot be empty.")
    @JsonProperty(value = "knjizna_polica_oznaka")
    private String knjiznaPolicaOznaka;
}
