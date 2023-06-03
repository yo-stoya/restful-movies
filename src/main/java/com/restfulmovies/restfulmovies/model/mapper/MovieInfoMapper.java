package com.restfulmovies.restfulmovies.model.mapper;

import com.restfulmovies.restfulmovies.model.dto.movieinfo.CreateMovieInfoDto;
import com.restfulmovies.restfulmovies.model.dto.movieinfo.MovieInfoDto;
import com.restfulmovies.restfulmovies.model.entity.MovieInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovieInfoMapper {

    @Mapping(target = "id", ignore = true)
    MovieInfo toEntity(CreateMovieInfoDto dto);

    MovieInfoDto toDto(MovieInfo movieInfo);

    @Mapping(target = "id", ignore = true)
    MovieInfo update(CreateMovieInfoDto dto, @MappingTarget MovieInfo movieInfo);


}
