package com.example.library_project.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.library_project.dto.ZaposlenDto;
import com.example.library_project.service.ZaposlenService;
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
@RequestMapping("diplomska_knjiznica/zaposlen")
public class ZaposlenController {
    private final ZaposlenService zaposlenService;

    @ApiResponse(code = 200, message = "Returned all data successfully.")
    @Operation(summary = "Get all zaposleni.")
    @GetMapping
    public CollectionModel<EntityModel<ZaposlenDto>> getAll() {
        List<ZaposlenDto> allzaposleni = zaposlenService.findAll();

        List<EntityModel<ZaposlenDto>> zaposleni =
                allzaposleni.stream()
                        .map(
                                zaposlen1 ->
                                        EntityModel.of(
                                                zaposlen1,
                                                linkTo(
                                                                methodOn(ZaposlenController.class)
                                                                        .returnZaposlenByZaposlenOznakaPogodbe(
                                                                                zaposlen1
                                                                                        .getZaposlenOznakaPogodbe()))
                                                        .withSelfRel()))
                        .collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(ZaposlenController.class).getAll()).withSelfRel();

        return CollectionModel.of(zaposleni, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "Zaposlen was found."),
                @ApiResponse(code = 404, message = "Zaposlen was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return zaposlen by oznaka.")
    @GetMapping("/{oznakapogodbe}")
    public EntityModel<ZaposlenDto> returnZaposlenByZaposlenOznakaPogodbe(
            @PathVariable(value = "oznakapogodbe") String oznaka) {
        ZaposlenDto zaposlen = zaposlenService.returnZaposlenByzaposlenOznakaPogodbe(oznaka);

        Link selfLink =
                linkTo(
                                methodOn(ZaposlenController.class)
                                        .returnZaposlenByZaposlenOznakaPogodbe(oznaka))
                        .withSelfRel();
        return EntityModel.of(zaposlen, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Zaposlen was created."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Create new zaposlen.")
    @PostMapping
    public ResponseEntity<ZaposlenDto> createZaposlenega(
            @Valid @RequestBody ZaposlenDto zaposlenDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(zaposlenService.createZaposlen(zaposlenDto));
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Zaposlen was updated."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Update zaposlenega.")
    @PutMapping("/{oznakapogodbe}")
    public ResponseEntity<ZaposlenDto> updateZaposlenega(
            @PathVariable(value = "oznakapogodbe") String oznaka,
            @Valid @RequestBody ZaposlenDto zaposlenDto) {
        return ResponseEntity.ok(zaposlenService.updateZaposlen(oznaka, zaposlenDto));
    }
}
