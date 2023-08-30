package com.example.library_project.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.library_project.dto.IzposojaDto;
import com.example.library_project.service.IzposojaService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Data
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("diplomska_knjiznica/izposoja")
public class IzposojaController {

    private final IzposojaService izposojaService;

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Izposoja was found."),
                @ApiResponse(code = 404, message = "Izposoja was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return izposoja by ID.")
    @GetMapping("/1/{id}")
    public EntityModel<IzposojaDto> returnIzposojaById(@PathVariable(value = "id") long id) {
        IzposojaDto izposoja = izposojaService.returnIzposojaByIzposojaId(id);

        Link selfLink =
                linkTo(methodOn(IzposojaController.class).returnIzposojaById(id)).withSelfRel();

        return EntityModel.of(izposoja, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Izposoja was found."),
                @ApiResponse(code = 404, message = "Izposoja was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return izposoja by username.")
    @GetMapping("/2/{username}")
    public CollectionModel<EntityModel<IzposojaDto>> returnIzposojaByUserUsername(
            @PathVariable(value = "username") String username) {
        List<IzposojaDto> allIzposoje = izposojaService.returnIzposojaByUserUsername(username);

        List<EntityModel<IzposojaDto>> izposoje =
                allIzposoje.stream()
                        .map(
                                izposoja ->
                                        EntityModel.of(
                                                izposoja,
                                                linkTo(
                                                                methodOn(IzposojaController.class)
                                                                        .returnIzposojaById(
                                                                                izposoja
                                                                                        .getIzposojaId()))
                                                        .withSelfRel()))
                        .collect(Collectors.toList());

        Link selfLink =
                linkTo(methodOn(IzposojaController.class).returnIzposojaByUserUsername(username))
                        .withSelfRel();

        return CollectionModel.of(izposoje, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Izposoja was created."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Create new izposoja.")
    @PostMapping("create_new_izposoja")
    public ResponseEntity<IzposojaDto> createIzposoja(@Valid @RequestBody IzposojaDto izposojaDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(izposojaService.createNewIzposoja(izposojaDto));
    }
}
