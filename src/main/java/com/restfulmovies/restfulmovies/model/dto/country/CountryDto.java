package com.restfulmovies.restfulmovies.model.dto.country;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CountryDto(

        @NotNull
        Long id,

        @NotBlank
        String name,

        @NotBlank
        String continent,

        @NotBlank
        String currency
) {
}
