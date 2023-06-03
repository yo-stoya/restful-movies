package com.restfulmovies.restfulmovies.web;

import com.restfulmovies.restfulmovies.model.dto.movieinfo.CreateMovieInfoDto;
import com.restfulmovies.restfulmovies.model.dto.movieinfo.MovieInfoDto;
import com.restfulmovies.restfulmovies.service.MovieInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/moviesinfo")
@AllArgsConstructor
@Validated
@Tag(name = "MoviesInfo")
public class MovieInfoController {

    private final MovieInfoService movieInfoService;

    @GetMapping
    public ResponseEntity<List<MovieInfoDto>> getAll() {
        return ResponseEntity.ok(movieInfoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieInfoDto> getOne(
            @PathVariable
            @NotNull
            @Positive
            Long id
    ) {
        return ResponseEntity.ok(movieInfoService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<MovieInfoDto> create(
            @RequestBody
            @Valid
            CreateMovieInfoDto dto,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        MovieInfoDto created = movieInfoService.create(dto);

        URI location = uriComponentsBuilder
                .path("/api/v1/moviesinfo/{id}")
                .build(created.id());

        return ResponseEntity
                .created(location)
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieInfoDto> update(
            @PathVariable
            @NotNull
            @Positive
            Long id,
            @RequestBody
            @Valid
            CreateMovieInfoDto dto
    ) {
        return ResponseEntity.ok(movieInfoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieInfoDto> delete(
            @PathVariable
            @NotNull
            @Positive
            Long id
    ) {
        movieInfoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
