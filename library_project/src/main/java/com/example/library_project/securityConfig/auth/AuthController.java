package com.example.library_project.securityConfig.auth;

import com.example.library_project.dto.UserDto;
import com.example.library_project.mappers.UserMapper;
import com.example.library_project.repository.UserRepository;
import com.example.library_project.securityConfig.jwt.JwtAuthenticationResponse;
import com.example.library_project.securityConfig.jwt.JwtResponse;
import com.example.library_project.securityConfig.jwt.JwtTokenUtil;
import com.example.library_project.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import java.time.Duration;
import javax.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Data
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/diplomska_knjiznica/auth")
public class AuthController {

    @Autowired private JwtTokenUtil jwtTokenUtil;

    @Autowired private AuthenticationManager authenticationManager;

    @Autowired private UserDetailsService userDetailsService;

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Login was created."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Login.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthLogin authLogin) {
        try {
            // Authenticate user with username and password from request body and set the
            // authentication in the security context
            authenticate(authLogin.getUsername(), authLogin.getPassword());
            final UserDetails userDetails =
                    userDetailsService.loadUserByUsername(authLogin.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);

            // Set token in auth header and cookies
            return setAuthHeaderAndCookies(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid credentials " + e.getMessage());
        }
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "User was created."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Create new user.")
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody AuthRegister authRegister) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        userService.registerUser(
                                authRegister.getEmail(),
                                authRegister.getUsername(),
                                authRegister.getPassword()));
    }

    @Operation(summary = "Authentificate user.")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthLogin authLogin)
            throws Exception {

        authenticate(authLogin.getUsername(), authLogin.getPassword());

        final UserDetails userDetails =
                userDetailsService.loadUserByUsername(authLogin.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER IS DISABLED");
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID CREDENTIALS");
        }
    }

    private ResponseEntity<?> setAuthHeaderAndCookies(String token) {
        // Set token in auth header and cookies
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        ResponseCookie jwtCookie =
                ResponseCookie.from("jwtToken", token)
                        .httpOnly(true)
                        .secure(true) // set this to true if using HTTPS
                        .maxAge(Duration.ofHours(3))
                        .build();

        headers.add(HttpHeaders.SET_COOKIE, jwtCookie.toString());
        JwtAuthenticationResponse response = new JwtAuthenticationResponse(token);

        return ResponseEntity.ok().headers(headers).body(response);
    }
}
