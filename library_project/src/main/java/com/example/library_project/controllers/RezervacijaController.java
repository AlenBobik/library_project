package com.example.library_project.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.library_project.dto.RezervacijaDto;
import com.example.library_project.service.RezervacijaService;
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
@RequestMapping("diplomska_knjiznica/rezervacija")
public class RezervacijaController {

    private final RezervacijaService rezervacijaService;

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Rezervacija was found."),
                @ApiResponse(code = 404, message = "Rezervacija was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return rezervacija by ID.")
    @GetMapping("/1/{id}")
    public EntityModel<RezervacijaDto> returnRezervacijaById(@PathVariable(value = "id") long id) {
        RezervacijaDto rezervacija = rezervacijaService.returnRezervacijaById(id);

        Link selfLink =
                linkTo(methodOn(RezervacijaController.class).returnRezervacijaById(id))
                        .withSelfRel();

        return EntityModel.of(rezervacija, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Rezervacija was found."),
                @ApiResponse(code = 404, message = "Rezervacija was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return rezervacija by username.")
    @GetMapping("/2/{username}")
    public CollectionModel<EntityModel<RezervacijaDto>> returnRezervacijaByUserUsername(
            @PathVariable(value = "username") String username) {
        List<RezervacijaDto> allRezervacije =
                rezervacijaService.returnRezervacijaByUserUsername(username);

        List<EntityModel<RezervacijaDto>> rezervacije =
                allRezervacije.stream()
                        .map(
                                rezervacija ->
                                        EntityModel.of(
                                                rezervacija,
                                                linkTo(
                                                                methodOn(
                                                                                RezervacijaController
                                                                                        .class)
                                                                        .returnRezervacijaById(
                                                                                rezervacija
                                                                                        .getRezervacijaId()))
                                                        .withSelfRel()))
                        .collect(Collectors.toList());

        Link selfLink =
                linkTo(
                                methodOn(RezervacijaController.class)
                                        .returnRezervacijaByUserUsername(username))
                        .withSelfRel();

        return CollectionModel.of(rezervacije, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Rezervacija was created."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Create new rezervacija.")
    @PostMapping("çreate_ner_rezervacija")
    public ResponseEntity<RezervacijaDto> createRezervacija(
            @Valid @RequestBody RezervacijaDto rezervacijaDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(rezervacijaService.createNewRezervacija(rezervacijaDto));
    }
}
