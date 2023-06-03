package com.restfulmovies.restfulmovies.model.mapper;

import com.restfulmovies.restfulmovies.model.dto.genre.CreateGenreDto;
import com.restfulmovies.restfulmovies.model.dto.genre.GenreDto;
import com.restfulmovies.restfulmovies.model.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    @Mapping(target = "id", ignore = true)
    Genre toEntity(CreateGenreDto dto);

    GenreDto toDto(Genre genre);

    List<GenreDto> toDtoList(List<Genre> genres);

    @Mapping(target = "id", ignore = true)
    Genre update(CreateGenreDto dto, @MappingTarget Genre genre);

}
