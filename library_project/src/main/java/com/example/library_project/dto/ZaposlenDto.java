package com.example.library_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZaposlenDto {

    @Length(min = 1, max = 20, message = "Zaposlen_oznaka_pogodbe length error.")
    @JsonProperty(value = "zaposlen_oznaka_pogodbe")
    private String zaposlenOznakaPogodbe;

    @Type(type = "date")
    @JsonProperty(value = "zaposlen_od")
    private Date zaposlenOd;

    @Type(type = "date")
    @JsonProperty(value = "zaposlen_do")
    private Date zaposlenDo;
}
