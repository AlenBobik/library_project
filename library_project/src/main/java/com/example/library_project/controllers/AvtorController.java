package com.example.library_project.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.library_project.dto.AvtorDto;
import com.example.library_project.service.AvtorService;
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
@RequestMapping("diplomska_knjiznica/avtor")
public class AvtorController {
    private final AvtorService avtorService;

    @ApiResponse(code = 200, message = "Returned all data successfully.")
    @Operation(summary = "Get all avtorje.")
    @GetMapping
    public CollectionModel<EntityModel<AvtorDto>> getAll() {
        List<AvtorDto> allAvtors = avtorService.findAll();

        List<EntityModel<AvtorDto>> avtorji =
                allAvtors.stream()
                        .map(
                                avtor ->
                                        EntityModel.of(
                                                avtor,
                                                linkTo(
                                                                methodOn(AvtorController.class)
                                                                        .returnAvtorByAvtorId(
                                                                                avtor.getAvtorId()))
                                                        .withSelfRel()))
                        .collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(AvtorController.class).getAll()).withSelfRel();

        return CollectionModel.of(avtorji, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Avtor was found."),
                @ApiResponse(code = 404, message = "Avtor was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return avtor by ID.")
    @GetMapping("/{id}")
    public EntityModel<AvtorDto> returnAvtorByAvtorId(@PathVariable(value = "id") long id) {
        AvtorDto avtor = avtorService.returnAvtorByAvtorId(id);

        Link selfLink =
                linkTo(methodOn(AvtorController.class).returnAvtorByAvtorId(id)).withSelfRel();

        return EntityModel.of(avtor, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Avtor was created."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Create new avtor.")
    @PostMapping
    public ResponseEntity<AvtorDto> createAvtor(@Valid @RequestBody AvtorDto avtorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avtorService.createAvtor(avtorDto));
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Avtor was updated."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Update avtor.")
    @PutMapping("/{id}")
    public ResponseEntity<AvtorDto> updateKnjigaIzvod(
            @PathVariable(value = "id") long id, @Valid @RequestBody AvtorDto avtorDto) {
        return ResponseEntity.ok(avtorService.updateAvtor(id, avtorDto));
    }
}
