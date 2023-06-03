package com.restfulmovies.restfulmovies.service.impl;

import com.restfulmovies.restfulmovies.exception.notfound.GenreNotFoundException;
import com.restfulmovies.restfulmovies.exception.notfound.MovieInfoNotFoundException;
import com.restfulmovies.restfulmovies.model.dto.movieinfo.CreateMovieInfoDto;
import com.restfulmovies.restfulmovies.model.dto.movieinfo.MovieInfoDto;
import com.restfulmovies.restfulmovies.model.entity.MovieInfo;
import com.restfulmovies.restfulmovies.model.mapper.MovieInfoMapper;
import com.restfulmovies.restfulmovies.model.repository.MovieInfoRepository;
import com.restfulmovies.restfulmovies.service.MovieInfoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieInfoServiceImpl implements MovieInfoService {

    private final MovieInfoRepository movieInfoRepository;
    private final MovieInfoMapper movieInfoMapper;

    private MovieInfo findMovieInfo(Long id) {
        return movieInfoRepository.findById(id).orElseThrow(() ->
                new MovieInfoNotFoundException(String.valueOf(id)));
    }

    @Override
    public List<MovieInfoDto> findAll() {
        return movieInfoRepository
                .findAll()
                .stream()
                .map(movieInfoMapper::toDto)
                .toList();
    }

    @Override
    public MovieInfoDto findOne(Long id) {
        return movieInfoMapper.toDto(findMovieInfo(id));
    }

    @Override
    public MovieInfoDto create(CreateMovieInfoDto dto) {
        var saved = movieInfoRepository.save(movieInfoMapper.toEntity(dto));
        return movieInfoMapper.toDto(saved);
    }

    @Override
    @Transactional
    public MovieInfoDto update(Long id, CreateMovieInfoDto dto) {
        var updated = movieInfoRepository.save(movieInfoMapper.update(dto, findMovieInfo(id)));
        return movieInfoMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        movieInfoRepository.deleteById(id);
    }
}
