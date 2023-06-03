package com.restfulmovies.restfulmovies.service.impl;

import com.restfulmovies.restfulmovies.exception.alreadyexist.MovieAlreadyExistException;
import com.restfulmovies.restfulmovies.exception.notfound.GenreNotFoundException;
import com.restfulmovies.restfulmovies.exception.notfound.MovieInfoNotFoundException;
import com.restfulmovies.restfulmovies.exception.notfound.MovieNotFoundException;
import com.restfulmovies.restfulmovies.model.dto.movie.CreateMovieDto;
import com.restfulmovies.restfulmovies.model.dto.movie.MovieDto;
import com.restfulmovies.restfulmovies.model.entity.Movie;
import com.restfulmovies.restfulmovies.model.mapper.MovieMapper;
import com.restfulmovies.restfulmovies.model.repository.MovieRepository;
import com.restfulmovies.restfulmovies.service.MovieService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    private Movie findMovie(Long id) {
        return movieRepository.findById(id).orElseThrow(() ->
                new MovieNotFoundException(
                String.valueOf(id)));
    }

    @Override
    public List<MovieDto> findAll() {
        return movieRepository
                .findAll()
                .stream()
                .map(movieMapper::toDto)
                .toList();
    }

    @Override
    public MovieDto findOne(Long id) {
        return movieMapper.toDto(findMovie(id));
    }

    @Override
    public MovieDto create(CreateMovieDto dto) {
        if (movieRepository.existsByTitle(dto.title())) {
            throw new MovieAlreadyExistException(dto.title());
        }

        var saved = movieRepository.save(movieMapper.toEntity(dto));
        return movieMapper.toDto(saved);
    }

    @Override
    @Transactional
    public MovieDto update(Long id, CreateMovieDto dto) {
        var updated = movieRepository.save(movieMapper.update(dto, findMovie(id)));
        return movieMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
}
