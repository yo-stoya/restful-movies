package com.restfulmovies.restfulmovies.model.mapper;

import com.restfulmovies.restfulmovies.model.dto.movie.CreateMovieDto;
import com.restfulmovies.restfulmovies.model.dto.movie.MovieDto;
import com.restfulmovies.restfulmovies.model.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface MovieMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", source = "country", qualifiedByName = "findCountry")
    @Mapping(target = "movieInfo", source = "movieInfo", qualifiedByName = "findMovieInfo")
    @Mapping(target = "genres", source = "genres", qualifiedByName = "findMovieGenres")
    @Mapping(target = "actors", source = "actors", qualifiedByName = "findMovieActors")
    Movie toEntity(CreateMovieDto dto);

    MovieDto toDto(Movie movie);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", source = "country", qualifiedByName = "findCountry")
    @Mapping(target = "movieInfo", source = "movieInfo", qualifiedByName = "findMovieInfo")
    @Mapping(target = "genres", source = "genres", qualifiedByName = "findMovieGenres")
    @Mapping(target = "actors", source = "actors", qualifiedByName = "findMovieActors")
    Movie update(CreateMovieDto dto, @MappingTarget Movie movie);
}
