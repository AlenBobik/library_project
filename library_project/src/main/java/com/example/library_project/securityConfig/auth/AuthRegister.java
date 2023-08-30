package com.example.library_project.securityConfig.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRegister {

    @NotEmpty(message = "Email cannot be empty.")
    @JsonProperty(value = "email")
    private String email;

    @NotEmpty(message = "Username cannot be empty.")
    @JsonProperty(value = "username")
    private String username;

    @NotEmpty(message = "Password cannot be empty.")
    @JsonProperty(value = "password")
    private String password;
}
