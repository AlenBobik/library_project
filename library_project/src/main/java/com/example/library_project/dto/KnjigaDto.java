package com.example.library_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnjigaDto {
    @JsonProperty(value = "knjiga_uuid", access = JsonProperty.Access.READ_ONLY)
    private UUID knjigaUuid;

    @JsonProperty(value = "knjiga_status", access = JsonProperty.Access.READ_ONLY)
    private String knjigaStatus;

    @Size(min = 1, max = 20, message = "KnjigaIzdaja length error.")
    @JsonProperty(value = "knjiga_vrsta_knjige")
    private String knjigaVrstaKnjige;

    @Size(min = 1, max = 20, message = "KnjigaIzdaja length error.")
    @JsonProperty(value = "knjiga_jezik")
    private String knjigaJezik;

    @Size(min = 1, max = 20, message = "KnjigaIzdaja length error.")
    @JsonProperty(value = "knjiga_izdaja")
    private String knjigaIzdaja;

    @NotBlank(message = "Knjiga izvod isbn must not be empty.")
    @JsonProperty(value = "knjiga_izvod_isbn")
    private String knjigaIzvodIsbn;

    @Type(type = "long")
    @JsonProperty(value = "knjiznica_id")
    private long knjiznicaId;
}
