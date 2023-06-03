package com.restfulmovies.restfulmovies.model.dto.genre;

import jakarta.validation.constraints.NotBlank;

public record CreateGenreDto(

        @NotBlank
        String name
) {
}
