package com.example.library_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Username must not be empty")
    @JsonProperty(value = "username")
    private String username;

    @NotBlank(message = "Uporabnisko_geslo must not be empty")
    @JsonProperty(value = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @JsonProperty(value = "users_status", access = JsonProperty.Access.READ_ONLY)
    private String userStatus;

    @Enumerated(value = EnumType.STRING)
    @JsonProperty(value = "role", access = JsonProperty.Access.READ_ONLY)
    private String role;

    @Email
    @NotBlank(message = "Oseba_email must not be empty")
    @JsonProperty(value = "oseba_email")
    private String osebaEmail;

    @Type(type = "long")
    @JsonProperty(value = "obvestilo_id", access = JsonProperty.Access.READ_ONLY)
    private long obvestiloId;

    @JsonProperty(value = "zaposlen_oznaka_pogodbe", access = JsonProperty.Access.READ_ONLY)
    private String zaposlenOznakaPogodbe;
}
