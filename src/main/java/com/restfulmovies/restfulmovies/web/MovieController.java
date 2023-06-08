package com.restfulmovies.restfulmovies.web;

import com.restfulmovies.restfulmovies.model.dto.actor.ActorDto;
import com.restfulmovies.restfulmovies.model.dto.movie.CreateMovieDto;
import com.restfulmovies.restfulmovies.model.dto.movie.MovieDto;
import com.restfulmovies.restfulmovies.service.MovieService;
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
@RequestMapping("/api/v1/movies")
@AllArgsConstructor
@Validated
@Tag(name = "Movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getOne(
            @PathVariable
            @NotNull
            @Positive
            Long id
    ) {
        return ResponseEntity.ok(movieService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MovieDto> create(
            @RequestBody
            @Valid
            CreateMovieDto dto,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        MovieDto created = movieService.create(dto);

        URI location = uriComponentsBuilder
                .path("/api/v1/movies/{id}")
                .build(created.id());

        return ResponseEntity
                .created(location)
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(
            @PathVariable
            @NotNull
            @Positive
            Long id,
            @RequestBody
            @Valid
            CreateMovieDto dto
    ) {
        return ResponseEntity.ok(movieService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActorDto> delete(
            @PathVariable
            @NotNull
            @Positive
            Long id
    ) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
