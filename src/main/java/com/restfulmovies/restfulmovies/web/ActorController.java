package com.restfulmovies.restfulmovies.web;

import com.restfulmovies.restfulmovies.model.dto.actor.ActorDto;
import com.restfulmovies.restfulmovies.model.dto.actor.CreateActorDto;
import com.restfulmovies.restfulmovies.service.ActorService;
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
@RequestMapping("/api/v1/actors")
@AllArgsConstructor
@Validated
@Tag(name = "Actors")
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public ResponseEntity<List<ActorDto>> getAll() {
        return ResponseEntity.ok(actorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDto> getOne(
            @PathVariable
            @NotNull
            @Positive
            Long id
    ) {
        return ResponseEntity.ok(actorService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<ActorDto> create(
            @RequestBody
            @Valid
            CreateActorDto dto,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        ActorDto created = actorService.create(dto);

        URI location = uriComponentsBuilder
                .path("/api/v1/actors/{id}")
                .build(created.id());

        return ResponseEntity
                .created(location)
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorDto> update(
            @PathVariable
            @NotNull
            @Positive
            Long id,
            @RequestBody
            @Valid
            CreateActorDto dto
    ) {
        return ResponseEntity.ok(actorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActorDto> delete(
            @PathVariable
            @NotNull
            @Positive
            Long id
    ) {
        actorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
