package com.restfulmovies.restfulmovies.service;


import com.restfulmovies.restfulmovies.model.dto.movieinfo.CreateMovieInfoDto;
import com.restfulmovies.restfulmovies.model.dto.movieinfo.MovieInfoDto;

import java.util.List;

public interface MovieInfoService {

    List<MovieInfoDto> findAll();

    MovieInfoDto findOne(Long id);

    MovieInfoDto create(CreateMovieInfoDto dto);

    MovieInfoDto update(Long id, CreateMovieInfoDto dto);

    void delete(Long id);
}
