package com.example.library_project.securityConfig.jwt;

import java.io.Serializable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
}
