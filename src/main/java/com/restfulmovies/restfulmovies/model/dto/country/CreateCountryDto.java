package com.restfulmovies.restfulmovies.model.dto.country;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record CreateCountryDto(

        @NotBlank
        String name,

        @NotBlank
        String continent,

        @NotBlank
        String currency
) {
}
