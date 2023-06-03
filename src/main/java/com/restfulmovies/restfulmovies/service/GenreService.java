package com.restfulmovies.restfulmovies.service;


import com.restfulmovies.restfulmovies.model.dto.genre.CreateGenreDto;
import com.restfulmovies.restfulmovies.model.dto.genre.GenreDto;

import java.util.List;
import java.util.Set;

public interface GenreService {
    List<GenreDto> findAll();

    GenreDto findOne(Long id);

    GenreDto create(CreateGenreDto dto);

    GenreDto update(Long id, CreateGenreDto dto);

    void delete(Long id);
}
