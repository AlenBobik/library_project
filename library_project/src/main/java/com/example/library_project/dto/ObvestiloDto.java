package com.example.library_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Type;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObvestiloDto {

    @JsonProperty(value = "obvestilo_id", access = JsonProperty.Access.READ_ONLY)
    private long obvestiloId;

    @NotBlank(message = "Obvestilo_ime cannot be empty.")
    @JsonProperty(value = "obvestilo_ime")
    private String obvestiloIme;

    @NotBlank(message = "Obvestilo_opis cannot be empty.")
    @JsonProperty(value = "obvestilo_opis")
    private String obvestiloOpis;

    @Type(type = "date")
    @JsonProperty(value = "obvestilo_datum")
    private Date obvestiloDatum;
}
