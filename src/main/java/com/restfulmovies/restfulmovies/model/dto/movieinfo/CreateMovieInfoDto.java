package com.restfulmovies.restfulmovies.model.dto.movieinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public record CreateMovieInfoDto(

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
        @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
        LocalDate releaseDate,

        boolean hasSubtitles,

        String description
) {
}
