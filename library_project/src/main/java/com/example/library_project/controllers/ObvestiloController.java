package com.example.library_project.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.library_project.dto.ObvestiloDto;
import com.example.library_project.service.ObvestiloService;
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
@RequestMapping("diplomska_knjiznica/obvestilo")
public class ObvestiloController {
    private final ObvestiloService obvestiloService;

    @ApiResponse(code = 200, message = "Returned all data successfully.")
    @Operation(summary = "Get all obvestila.")
    @GetMapping
    public CollectionModel<EntityModel<ObvestiloDto>> getAll() {
        List<ObvestiloDto> allObvestila = obvestiloService.findAll();

        List<EntityModel<ObvestiloDto>> obvestila =
                allObvestila.stream()
                        .map(
                                obvestilo ->
                                        EntityModel.of(
                                                obvestilo,
                                                linkTo(
                                                                methodOn(ObvestiloController.class)
                                                                        .returnObvestiloByObvestiloId(
                                                                                obvestilo
                                                                                        .getObvestiloId()))
                                                        .withSelfRel()))
                        .collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(ObvestiloController.class).getAll()).withSelfRel();

        return CollectionModel.of(obvestila, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Obvestilo was found."),
                @ApiResponse(code = 404, message = "Obvestilo was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return Obvestilo by id.")
    @GetMapping("/{obvestiloid}")
    public EntityModel<ObvestiloDto> returnObvestiloByObvestiloId(
            @PathVariable(value = "obvestiloid") long id) {
        ObvestiloDto obvestilo = obvestiloService.returnObvestiloByObvestiloId(id);

        Link selfLink =
                linkTo(methodOn(ObvestiloController.class).returnObvestiloByObvestiloId(id))
                        .withSelfRel();

        return EntityModel.of(obvestilo, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Obvestilo was created."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Create new obvestilo.")
    @PostMapping
    public ResponseEntity<ObvestiloDto> createObvestilo(
            @Valid @RequestBody ObvestiloDto obvestiloDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(obvestiloService.createObvestilo(obvestiloDto));
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 204, message = "Obvestilo was deleted"),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 404, message = "Obvestilo was not found."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Update obvestilo.")
    @PutMapping("/{obvestiloid}")
    public ResponseEntity<ObvestiloDto> updateObvestilo(
            @PathVariable(value = "obvestiloid") long id,
            @Valid @RequestBody ObvestiloDto obvestiloDto) {
        return ResponseEntity.ok(obvestiloService.updateObvestilo(id, obvestiloDto));
    }
}
