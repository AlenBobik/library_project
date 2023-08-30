package com.example.library_project.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.library_project.dto.KnjigaDto;
import com.example.library_project.dto.OsebaDto;
import com.example.library_project.enums.OsebaSpol;
import com.example.library_project.service.OsebaService;
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
@RequestMapping("diplomska_knjiznica/oseba")
public class OsebaController {
    private final OsebaService osebaService;

    @ApiResponse(code = 200, message = "Returned all data successfully.")
    @Operation(summary = "Get all Osebe.")
    @GetMapping
    public CollectionModel<EntityModel<OsebaDto>> getAll() {
        List<OsebaDto> allOsebe = osebaService.findAll();

        List<EntityModel<OsebaDto>> osebe =
                allOsebe.stream()
                        .map(
                                oseba1 ->
                                        EntityModel.of(
                                                oseba1,
                                                linkTo(
                                                                methodOn(OsebaController.class)
                                                                        .returnOsebaByEmail(
                                                                                oseba1
                                                                                        .getOsebaEmail()))
                                                        .withSelfRel()))
                        .collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(OsebaController.class).getAll()).withSelfRel();

        return CollectionModel.of(osebe, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Oseba was found."),
                @ApiResponse(code = 404, message = "Oseba was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return oseba by email.")
    @GetMapping("/email/{email}")
    public EntityModel<OsebaDto> returnOsebaByEmail(@PathVariable(value = "email") String email) {
        OsebaDto osebaDto = osebaService.returnByOsebaEmail(email);

        Link selfLink =
                linkTo(methodOn(OsebaController.class).returnOsebaByEmail(email)).withSelfRel();

        return EntityModel.of(osebaDto, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Oseba was created."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Create new oseba.")
    @PostMapping
    public ResponseEntity<OsebaDto> createOseba(
            OsebaSpol spol, @Valid @RequestBody OsebaDto osebaDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(osebaService.createOseba(spol, osebaDto));
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Oseba was updated."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Update oseba.")
    @PutMapping("/{emso}")
    public ResponseEntity<OsebaDto> updateOseba(
            @PathVariable(value = "emso") long emso, @Valid @RequestBody OsebaDto osebaDto) {
        return ResponseEntity.ok(osebaService.updateOseba(emso, osebaDto));
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 204, message = "Oseba was deleted"),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 404, message = "Oseba was not found."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Delete oseba.")
    @DeleteMapping("/{emso}")
    public ResponseEntity<KnjigaDto> deleteOseba(@PathVariable(value = "emso") long emso) {
        osebaService.deleteOseba(emso);

        return ResponseEntity.noContent().build();
    }
}
