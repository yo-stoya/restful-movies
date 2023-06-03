package com.restfulmovies.restfulmovies.model.dto.movie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public record CreateMovieDto(

        @NotBlank
        String title,

        @NotNull
        @Positive
        Long country,

        @NotNull
        @Positive
        Long movieInfo,

        @NotEmpty
        Set<Long> genres,

        @NotEmpty
        Set<Long> actors
) {
}
