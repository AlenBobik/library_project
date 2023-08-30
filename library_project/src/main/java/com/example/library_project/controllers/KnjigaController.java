package com.example.library_project.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.library_project.dto.KnjigaDto;
import com.example.library_project.service.KnjigaService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.UUID;
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
@RequestMapping("diplomska_knjiznica/knjiga")
public class KnjigaController {
    private final KnjigaService knjigaService;

    @ApiResponse(code = 200, message = "Returned all data successfully.")
    @Operation(summary = "Get all knjige.")
    @GetMapping
    public CollectionModel<EntityModel<KnjigaDto>> getAll() {
        List<KnjigaDto> allBooks = knjigaService.findAll();

        List<EntityModel<KnjigaDto>> knjige =
                allBooks.stream()
                        .map(
                                knjiga ->
                                        EntityModel.of(
                                                knjiga,
                                                linkTo(
                                                                methodOn(KnjigaController.class)
                                                                        .returnKnjigaByKnjigaUuid(
                                                                                knjiga
                                                                                        .getKnjigaUuid()))
                                                        .withSelfRel()))
                        .collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(KnjigaController.class).getAll()).withSelfRel();

        return CollectionModel.of(knjige, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Knjiga was found."),
                @ApiResponse(code = 404, message = "Knjiga was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return Knjiga by UUID.")
    @GetMapping("/{knjigauuid}")
    public EntityModel<KnjigaDto> returnKnjigaByKnjigaUuid(
            @PathVariable(value = "knjigauuid") UUID uuid) {
        KnjigaDto knjiga = knjigaService.returnKnjigaByKnjigaUuid(uuid);
        Link selfLink =
                linkTo(methodOn(KnjigaController.class).returnKnjigaByKnjigaUuid(uuid))
                        .withSelfRel();
        return EntityModel.of(knjiga, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Knjiga was created."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Create new knjiga.")
    @PostMapping
    public ResponseEntity<KnjigaDto> createKnjiga(@Valid @RequestBody KnjigaDto knjigaDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(knjigaService.createKnjiga(knjigaDto));
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Knjiga was updated."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Update knjiga.")
    @PutMapping("/{knjigauuid}")
    public ResponseEntity<KnjigaDto> updateKnjiga(
            @PathVariable(value = "knjigauuid") UUID uuid,
            @Valid @RequestBody KnjigaDto knjigaDto) {
        return ResponseEntity.ok(knjigaService.updateKnjiga(uuid, knjigaDto));
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 204, message = "Knjiga was deleted"),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 404, message = "Knjiga was not found."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Delete a knjiga.")
    @DeleteMapping("/{knjigauuid}")
    public ResponseEntity<KnjigaDto> deleteKnjiga(@PathVariable(value = "knjigauuid") UUID uuid) {
        knjigaService.deleteKnjiga(uuid);

        return ResponseEntity.noContent().build();
    }
}
