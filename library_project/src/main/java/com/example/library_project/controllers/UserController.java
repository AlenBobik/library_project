package com.example.library_project.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.library_project.dto.UserDto;
import com.example.library_project.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Data
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("diplomska_knjiznica/uporabniskiracun")
public class UserController {
    private final UserService userService;

    @ApiResponse(code = 200, message = "Returned all data successfully.")
    @Operation(summary = "Get all users.")
    @GetMapping
    public CollectionModel<EntityModel<UserDto>> getAll() {
        List<UserDto> allUporabiki = userService.findAll();

        List<EntityModel<UserDto>> users =
                allUporabiki.stream()
                        .map(
                                user ->
                                        EntityModel.of(
                                                user,
                                                linkTo(
                                                                methodOn(UserController.class)
                                                                        .returnUserByUserEmail(
                                                                                user
                                                                                        .getOsebaEmail()))
                                                        .withSelfRel()))
                        .collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(UserController.class).getAll()).withSelfRel();

        return CollectionModel.of(users, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "User was found."),
                @ApiResponse(code = 404, message = "User was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return user by email.")
    @GetMapping("/email/{email}")
    public EntityModel<UserDto> returnUserByUserEmail(@PathVariable(value = "email") String email) {
        UserDto racun = userService.returnByUserEmail(email);

        Link selfLink =
                linkTo(methodOn(UserController.class).returnUserByUserEmail(email)).withSelfRel();

        return EntityModel.of(racun, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "User was found."),
                @ApiResponse(code = 404, message = "User was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return user by username.")
    @GetMapping("/username/{username}")
    public EntityModel<UserDto> returnUserByUsername(
            @PathVariable(value = "username") String username) {
        UserDto racun = userService.returnByUsername(username);

        Link selfLink =
                linkTo(methodOn(UserController.class).returnUserByUsername(username)).withSelfRel();

        return EntityModel.of(racun, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "User was updated."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Update user.")
    @PutMapping("/{email}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable(value = "email") String email,
            @Valid @RequestBody UserDto uporabniskiRacunDto) {
        return ResponseEntity.ok(userService.updateUser(email, uporabniskiRacunDto));
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "User was Deleted."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Delete user.")
    @DeleteMapping("/{email}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable(value = "email") String email) {
        userService.deleteUser(email);

        return ResponseEntity.noContent().build();
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "User was updated."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Add employe to user")
    @PutMapping("/uporabniskiracun/{email}/zaposlen/{oznakapogodbe}")
    public ResponseEntity<UserDto> dodajZaposlenegaUserju(
            @PathVariable(value = "email") String email,
            @PathVariable(value = "oznakapogodbe") String oznaka) {
        return ResponseEntity.ok(userService.dodajZaposlenegaUserju(email, oznaka));
    }
}
