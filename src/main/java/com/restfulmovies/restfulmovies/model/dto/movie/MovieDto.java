package com.restfulmovies.restfulmovies.model.dto.movie;

import com.restfulmovies.restfulmovies.model.dto.actor.ActorDto;
import com.restfulmovies.restfulmovies.model.dto.country.CountryDto;
import com.restfulmovies.restfulmovies.model.dto.genre.GenreDto;
import com.restfulmovies.restfulmovies.model.dto.movieinfo.MovieInfoDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record MovieDto(

        @NotNull
        Long id,

        @NotBlank
        String title,

        @NotNull
        CountryDto country,

        @NotNull
        MovieInfoDto movieInfo,

        @NotEmpty
        Set<GenreDto> genres,

        @NotEmpty
        Set<ActorDto> actors
) {
}
