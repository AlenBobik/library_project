package com.example.library_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OsebaDto {

    @NotBlank(message = "Oseba_ime must not be empty")
    @JsonProperty(value = "oseba_ime")
    private String osebaIme;

    @NotBlank(message = "Oseba_priimek must not be empty")
    @JsonProperty(value = "oseba_priimek")
    private String osebaPriimek;

    @Type(type = "date")
    @JsonProperty(value = "oseba_datum_rojstva")
    private LocalDate osebaDatumRojstva;

    @JsonProperty(value = "oseba_spol", access = JsonProperty.Access.READ_ONLY)
    private String osebaSpol;

    @Type(type = "long")
    @JsonProperty(value = "oseba_emso")
    private long osebaEmso;

    @Email(message = "Input value is not a valid e-mail.")
    @NotBlank(message = "Oseba_e_mail must not be empty")
    @JsonProperty(value = "oseba_e_mail")
    private String osebaEmail;

    @Size(min = 9, max = 9, message = "Oseba_telefon length error.")
    @JsonProperty(value = "oseba_telefon")
    private String osebaTelefon;

    @JsonProperty(value = "oseba_status", access = JsonProperty.Access.READ_ONLY)
    private String osebaStatus;

    @JsonProperty(value = "izkaznica_oznaka", access = JsonProperty.Access.READ_ONLY)
    private String izkaznicaOznaka;
}
