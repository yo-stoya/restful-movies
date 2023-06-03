package com.restfulmovies.restfulmovies.model.dto.genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GenreDto(

        @NotNull
        Long id,

        @NotBlank
        String name
) {
}
