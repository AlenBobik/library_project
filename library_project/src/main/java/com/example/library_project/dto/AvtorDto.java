package com.example.library_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvtorDto {

    @JsonProperty(value = "avtor_id", access = JsonProperty.Access.READ_ONLY)
    private long avtorId;

    @NotEmpty(message = "Avtor_ime cannot be empty.")
    @JsonProperty(value = "avtor_ime_priimek")
    private String avtorImePriimek;
}
