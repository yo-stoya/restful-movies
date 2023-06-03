package com.restfulmovies.restfulmovies.service.impl;

import com.restfulmovies.restfulmovies.exception.alreadyexist.GenreAlreadyExistException;
import com.restfulmovies.restfulmovies.exception.notfound.GenreNotFoundException;
import com.restfulmovies.restfulmovies.model.dto.genre.CreateGenreDto;
import com.restfulmovies.restfulmovies.model.dto.genre.GenreDto;
import com.restfulmovies.restfulmovies.model.entity.Genre;
import com.restfulmovies.restfulmovies.model.mapper.GenreMapper;
import com.restfulmovies.restfulmovies.model.repository.GenreRepository;
import com.restfulmovies.restfulmovies.service.GenreService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    private Genre findGenre(Long id) {
        return genreRepository.findById(id).orElseThrow(() ->
                new GenreNotFoundException(String.valueOf(id)));
    }

    @Override
    public List<GenreDto> findAll() {
        return genreRepository
                .findAll()
                .stream()
                .map(genreMapper::toDto)
                .toList();
    }

    @Override
    public GenreDto findOne(Long id) {
        return genreMapper.toDto(findGenre(id));
    }

    @Override
    public GenreDto create(CreateGenreDto dto) {
        if (genreRepository.existsByName(dto.name())) {
            throw new GenreAlreadyExistException(dto.name());
        }

        var saved = genreRepository.save(genreMapper.toEntity(dto));
        return genreMapper.toDto(saved);
    }

    @Override
    @Transactional
    public GenreDto update(Long id, CreateGenreDto dto) {
        var updated = genreRepository.save(genreMapper.update(dto, findGenre(id)));
        return genreMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }
}
