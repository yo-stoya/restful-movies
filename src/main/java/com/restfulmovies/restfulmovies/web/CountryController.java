package com.restfulmovies.restfulmovies.web;

import com.restfulmovies.restfulmovies.model.dto.country.CountryDto;
import com.restfulmovies.restfulmovies.model.dto.country.CreateCountryDto;
import com.restfulmovies.restfulmovies.service.CountryService;
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
@RequestMapping("/api/v1/countries")
@AllArgsConstructor
@Validated
@Tag(name = "Countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryDto>> getAll() {
        return ResponseEntity.ok(countryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getOne(
            @PathVariable
            @NotNull
            @Positive
            Long id
    ) {
        return ResponseEntity.ok(countryService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<CountryDto> create(
            @RequestBody
            @Valid
            CreateCountryDto dto,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        CountryDto created = countryService.create(dto);
        URI location = uriComponentsBuilder
                .path("/api/v1/countries/{id}")
                .build(created.id());

        return ResponseEntity
                .created(location)
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDto> update(
            @PathVariable
            @NotNull
            @Positive
            Long id,
            @RequestBody
            @Valid
            CreateCountryDto dto
    ) {
        return ResponseEntity.ok(countryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CountryDto> delete(
            @PathVariable
            @NotNull
            @Positive
            Long id
    ) {
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
