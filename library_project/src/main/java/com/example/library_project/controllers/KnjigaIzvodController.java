package com.example.library_project.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.library_project.dto.KnjigaIzvodDto;
import com.example.library_project.service.KnjigaIzvodService;
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
// @CrossOrigin(origins = {"http://127.0.0.1:5454", "http://localhost:5454"})
@RequestMapping("diplomska_knjiznica/knjigaizvod")
public class KnjigaIzvodController {
    private final KnjigaIzvodService knjigaIzvodService;

    @ApiResponse(code = 200, message = "Returned all data successfully.")
    @Operation(summary = "Get all KnjigeIzvod.")
    @GetMapping
    public CollectionModel<EntityModel<KnjigaIzvodDto>> getAll() {
        List<KnjigaIzvodDto> allBooks = knjigaIzvodService.findAll();

        List<EntityModel<KnjigaIzvodDto>> knjigeIzvod =
                allBooks.stream()
                        .map(
                                knjigaIzvod ->
                                        EntityModel.of(
                                                knjigaIzvod,
                                                linkTo(
                                                                methodOn(
                                                                                KnjigaIzvodController
                                                                                        .class)
                                                                        .returnKnjigaIzvodByKnjigaIzvodIsbn(
                                                                                knjigaIzvod
                                                                                        .getKnjigaIzvodIsbn()))
                                                        .withSelfRel()))
                        .collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(KnjigaIzvodController.class).getAll()).withSelfRel();

        return CollectionModel.of(knjigeIzvod, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "KnjigaIzvod was found."),
                @ApiResponse(code = 404, message = "KnjigaIzvod was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return KnjigaIzvod by ISBN.")
    @GetMapping("/knjigaisbn/{isbn}")
    public EntityModel<KnjigaIzvodDto> returnKnjigaIzvodByKnjigaIzvodIsbn(
            @PathVariable(value = "isbn") String isbn) {
        KnjigaIzvodDto knjigaIzvod = knjigaIzvodService.returnKnjigaIzvodByKnjigaIzvodIsbn(isbn);

        Link selfLink =
                linkTo(
                                methodOn(KnjigaIzvodController.class)
                                        .returnKnjigaIzvodByKnjigaIzvodIsbn(isbn))
                        .withSelfRel();

        return EntityModel.of(knjigaIzvod, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "KnjigaIzvod was found."),
                @ApiResponse(code = 404, message = "KnjigaIzvod was not found."),
                @ApiResponse(code = 500, message = "Internal server error."),
            })
    @Operation(summary = "Return KnjigaIzvod by naslov.")
    @GetMapping("/knjiganaslov/{naslov}")
    public CollectionModel<EntityModel<KnjigaIzvodDto>> returnKnjigaIzvodByKnjigaIzvodNaslov(
            @PathVariable(value = "naslov") String naslov) {
        List<KnjigaIzvodDto> allBooks =
                knjigaIzvodService.returnKnjigaIzvodByKnjigaIzvodNaslov(naslov);

        List<EntityModel<KnjigaIzvodDto>> knjigeIzvod =
                allBooks.stream()
                        .map(
                                knjigaIzvod ->
                                        EntityModel.of(
                                                knjigaIzvod,
                                                linkTo(
                                                                methodOn(
                                                                                KnjigaIzvodController
                                                                                        .class)
                                                                        .returnKnjigaIzvodByKnjigaIzvodIsbn(
                                                                                knjigaIzvod
                                                                                        .getKnjigaIzvodIsbn()))
                                                        .withSelfRel()))
                        .collect(Collectors.toList());

        Link selfLink =
                linkTo(
                                methodOn(KnjigaIzvodController.class)
                                        .returnKnjigaIzvodByKnjigaIzvodNaslov(naslov))
                        .withSelfRel();

        return CollectionModel.of(knjigeIzvod, selfLink);
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "KnjigaIzvod was created."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Create new KnjigaIzvod.")
    @PostMapping
    public ResponseEntity<KnjigaIzvodDto> createKnjigaIzvod(
            @Valid @RequestBody KnjigaIzvodDto knjigaIzvodDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(knjigaIzvodService.createKnjigaIzvod(knjigaIzvodDto));
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "KnjigaIzvod was updated."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Update knjigaIzvod.")
    @PutMapping("/{isbn}")
    public ResponseEntity<KnjigaIzvodDto> updateKnjigaIzvod(
            @PathVariable(value = "isbn") String isbn,
            @Valid @RequestBody KnjigaIzvodDto knjigaIzvodDto) {
        return ResponseEntity.ok(knjigaIzvodService.updateKnjigaIzvod(isbn, knjigaIzvodDto));
    }

    @ApiResponses(
            value = {
                @ApiResponse(code = 201, message = "Avtor was added."),
                @ApiResponse(code = 400, message = "Bad request."),
                @ApiResponse(code = 409, message = "Conflict."),
                @ApiResponse(code = 500, message = "Internal server error.")
            })
    @Operation(summary = "Add Avtor to KnjigaIzvod.")
    @PutMapping("/{knjigaizvodisbn}/avtor/{avtorid}")
    public ResponseEntity<KnjigaIzvodDto> dodajAvtorjaKnjigiIzvod(
            @PathVariable(value = "knjigaizvodisbn") String knjigaIzvodisbn,
            @PathVariable(value = "avtorid") long avtorId) {
        return ResponseEntity.ok(
                knjigaIzvodService.dodajAvtorjaKnjigiIzvod(knjigaIzvodisbn, avtorId));
    }
}
