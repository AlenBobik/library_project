package com.example.library_project.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.library_project.dto.IzkaznicaDto;
import com.example.library_project.service.IzkaznicaService;
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
@RequestMapping("diplomska_knjiznica/izkaznica")
public class IzkaznicaController {
    private final IzkaznicaService izkaznicaService;

    @ApiResponse(code = 200, message = "Returned all data successfully.")
    @Operation(summary = "Get all izkaznice.")
    @GetMapping
    public CollectionModel<EntityModel<IzkaznicaDto>> getAll() {
        List<IzkaznicaDto> allIzkaznice = izkaznicaService.findAll();

        List<EntityModel<IzkaznicaDto>> izkaznice =
                allIzkaznice.stream()
                        .map(
                                izkaznica1 ->
                                        EntityModel.of(
                                                izkaznica1,
                                                linkTo(
                                                                methodOn(IzkaznicaController.class)
                                                                        .returnIzkaznicaByIzkaznicaOznaka(
                                                                                izkaznica1
                                                                                        .getIzkaznicaOznaka()))
                                                        .withSelfRel()))
                        .collect(Collectors.toList());

        Link sefLink = linkTo(methodOn(IzkaznicaController.class).getAll()).withSelfRel();

        return CollectionModel.of(izkaznice, sefLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Izkaznica was found."),
                @ApiResponse(code = 404, message = "Izkaznica was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return Izkaznica by oznaka.")
    @GetMapping("/{izkaznicaoznaka}")
    public EntityModel<IzkaznicaDto> returnIzkaznicaByIzkaznicaOznaka(
            @PathVariable(value = "izkaznicaoznaka") String oznaka) {
        IzkaznicaDto izkaznica = izkaznicaService.returnIzkaznicaByIzkaznicaOznaka(oznaka);

        Link selflink =
                linkTo(methodOn(IzkaznicaController.class).returnIzkaznicaByIzkaznicaOznaka(oznaka))
                        .withSelfRel();

        return EntityModel.of(izkaznica, selflink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Izkaznica was created."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Create new izkaznica.")
    @PostMapping
    public ResponseEntity<IzkaznicaDto> createIzkaznica() {
        return ResponseEntity.status(HttpStatus.CREATED).body(izkaznicaService.createIzkaznica());
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Izkaznica was updated."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Update izkaznica.")
    @PutMapping("/{izkaznicaoznaka}")
    public ResponseEntity<IzkaznicaDto> updateIzkaznica(
            @PathVariable(value = "izkaznicaoznaka") String oznaka,
            @Valid @RequestBody IzkaznicaDto izkaznicaDto) {
        return ResponseEntity.ok(izkaznicaService.updateIzkaznica(oznaka, izkaznicaDto));
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 204, message = "Izkaznica was deleted"),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 404, message = "Izkaznica was not found."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Delete izkaznica.")
    @DeleteMapping("/{izkaznicaoznaka}")
    public ResponseEntity<IzkaznicaDto> deleteIzkaznica(
            @PathVariable(value = "izkaznicaoznaka") String oznaka) {
        izkaznicaService.deleteIzkaznica(oznaka);

        return ResponseEntity.noContent().build();
    }
}
