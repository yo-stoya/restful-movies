package com.restfulmovies.restfulmovies.web;

import com.restfulmovies.restfulmovies.model.dto.genre.CreateGenreDto;
import com.restfulmovies.restfulmovies.model.dto.genre.GenreDto;
import com.restfulmovies.restfulmovies.service.GenreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
@AllArgsConstructor
@Validated
@Tag(name = "Genres")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAll() {
        return ResponseEntity.ok(genreService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getOne(
            @PathVariable
            @NotNull
            @Positive
            Long id
    ) {
        return ResponseEntity.ok(genreService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GenreDto> create(
            @RequestBody
            @Valid
            CreateGenreDto dto,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        GenreDto created = genreService.create(dto);

        URI location = uriComponentsBuilder
                .path("/api/v1/genres/{id}")
                .build(created.id());

        return ResponseEntity
                .created(location)
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDto> update(
            @PathVariable
            @NotNull
            @Positive
            Long id,
            @RequestBody
            @Valid
            CreateGenreDto dto
    ) {
        return ResponseEntity.ok(genreService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenreDto> delete(
            @PathVariable
            @NotNull
            @Positive
            Long id
    ) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
