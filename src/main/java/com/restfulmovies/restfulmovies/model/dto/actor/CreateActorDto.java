package com.restfulmovies.restfulmovies.model.dto.actor;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public record CreateActorDto(

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotNull
        @PastOrPresent
        @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
        LocalDate birthdate,

        @Positive
        int height,


        int awards,

        @NotNull
        @Positive
        Long country
) {
}
