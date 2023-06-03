package com.restfulmovies.restfulmovies.service;


import com.restfulmovies.restfulmovies.model.dto.movie.CreateMovieDto;
import com.restfulmovies.restfulmovies.model.dto.movie.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll();

    MovieDto findOne(Long id);

    MovieDto create(CreateMovieDto dto);

    MovieDto update(Long id, CreateMovieDto dto);

    void delete(Long id);
}
