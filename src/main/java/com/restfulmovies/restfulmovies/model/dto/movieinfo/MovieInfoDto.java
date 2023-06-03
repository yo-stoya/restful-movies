package com.restfulmovies.restfulmovies.model.dto.movieinfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovieInfoDto(

        @NotNull
        Long id,

        @NotNull
        @Positive
        int rating,

        @NotNull
        @Positive
        int runtime,

        @NotBlank
        String thumbnail,

        @Positive
        BigDecimal budget,

        @NotNull
        LocalDate releaseDate,

        boolean hasSubtitles,

        String description
) {
}
