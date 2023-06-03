package com.restfulmovies.restfulmovies.model.dto.actor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.restfulmovies.restfulmovies.model.dto.country.CountryDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public record ActorDto(

        @NotNull
        Long id,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        @PastOrPresent
        @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
        LocalDate birthdate,

        @Positive
        int height,

        int awards,

        CountryDto country
) {
}
