package com.example.library_project.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.library_project.dto.KnjiznicaDto;
import com.example.library_project.service.KnjiznicaService;
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
@RequestMapping("diplomska_knjiznica/knjiznica")
public class KnjiznicaController {
    private final KnjiznicaService knjiznicaService;

    @ApiResponse(code = 200, message = "Returned all data successfully.")
    @Operation(summary = "Get all Knjiznice.")
    @GetMapping
    public CollectionModel<EntityModel<KnjiznicaDto>> getAll() {
        List<KnjiznicaDto> allKnjiznice = knjiznicaService.findAll();

        List<EntityModel<KnjiznicaDto>> knjiznica =
                allKnjiznice.stream()
                        .map(
                                knjiznica1 ->
                                        EntityModel.of(
                                                knjiznica1,
                                                linkTo(
                                                                methodOn(KnjiznicaController.class)
                                                                        .returnKnjiznicaByKnjiznicaId(
                                                                                knjiznica1
                                                                                        .getKnjiznicaId()))
                                                        .withSelfRel()))
                        .collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(KnjiznicaController.class).getAll()).withSelfRel();

        return CollectionModel.of(knjiznica, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Knjiznica was found."),
                @ApiResponse(code = 404, message = "Knjiznica was not found."),
                @ApiResponse(code = 505, message = "Internal server error."),
            })
    @Operation(summary = "Return Knjiznica by ID.")
    @GetMapping("/{knjiznicaid}")
    public EntityModel<KnjiznicaDto> returnKnjiznicaByKnjiznicaId(
            @PathVariable(value = "knjiznicaid") long id) {
        KnjiznicaDto knjiznicaDto = knjiznicaService.returnKnjiznicaByKnjiznicaId(id);

        Link selfLink =
                linkTo(methodOn(KnjiznicaController.class).returnKnjiznicaByKnjiznicaId(id))
                        .withSelfRel();

        return EntityModel.of(knjiznicaDto, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Knjiznica was created."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Create new knjiznica.")
    @PostMapping
    public ResponseEntity<KnjiznicaDto> createKnjiznica(
            @Valid @RequestBody KnjiznicaDto knjiznicaDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(knjiznicaService.createKnjiznica(knjiznicaDto));
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Knjiznica was updated."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Update knjiznica.")
    @PutMapping("/{knjiznicaid}")
    public ResponseEntity<KnjiznicaDto> updateKnjiznica(
            @PathVariable(value = "knjiznicaid") long knjiznica_id,
            @Valid @RequestBody KnjiznicaDto knjiznicaDto) {
        return ResponseEntity.ok(knjiznicaService.updateKnjiznica(knjiznica_id, knjiznicaDto));
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 204, message = "Knjiznica was deleted"),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 404, message = "Knjiznica was not found."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Delete a knjiznica.")
    @DeleteMapping("/{knjiznicaid}")
    public ResponseEntity<KnjiznicaDto> deleteKnjiznica(
            @PathVariable(value = "knjiznicaid") long knjiznica_id) {
        knjiznicaService.deleteKnjiznica(knjiznica_id);

        return ResponseEntity.noContent().build();
    }
}
