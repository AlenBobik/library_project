package com.example.library_project.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.library_project.dto.KnjiznaPolicaDto;
import com.example.library_project.service.KnjiznaPolicaService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("diplomska_knjiznica/knjizna_polica")
public class KnjiznaPolicaController {
    private final KnjiznaPolicaService knjiznaPolicaService;

    @ApiResponse(code = 200, message = "Returned all data successfully.")
    @Operation(summary = "Get all knjiznePolice.")
    @GetMapping
    public CollectionModel<EntityModel<KnjiznaPolicaDto>> getAll() {
        List<KnjiznaPolicaDto> allKnjiznePolice = knjiznaPolicaService.findAll();

        List<EntityModel<KnjiznaPolicaDto>> knjiznePolice =
                allKnjiznePolice.stream()
                        .map(
                                knjiznaPolica1 ->
                                        EntityModel.of(
                                                knjiznaPolica1,
                                                linkTo(
                                                                methodOn(
                                                                                KnjiznaPolicaController
                                                                                        .class)
                                                                        .returnKnjiznaPolicaByKnjiznaPolicaOznaka(
                                                                                knjiznaPolica1
                                                                                        .getKnjiznaPolicaOznaka()))
                                                        .withSelfRel()))
                        .collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(KnjiznaPolicaController.class).getAll()).withSelfRel();

        return CollectionModel.of(knjiznePolice, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "KnjiznaPolica was found."),
                @ApiResponse(code = 404, message = "KnjiznaPolica was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return knjiznaPolica by oznaka.")
    @GetMapping("/{oznaka}")
    public EntityModel<KnjiznaPolicaDto> returnKnjiznaPolicaByKnjiznaPolicaOznaka(
            @PathVariable(value = "oznaka") String oznaka) {
        KnjiznaPolicaDto knjiznaPolicaDto =
                knjiznaPolicaService.returnKnjiznaPolicaByKnjiznaPolicaOznaka(oznaka);

        Link selfLink =
                linkTo(
                                methodOn(KnjiznaPolicaController.class)
                                        .returnKnjiznaPolicaByKnjiznaPolicaOznaka(oznaka))
                        .withSelfRel();

        return EntityModel.of(knjiznaPolicaDto, selfLink);
    }
}
