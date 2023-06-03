package com.restfulmovies.restfulmovies.service;

import com.restfulmovies.restfulmovies.exception.alreadyexist.GenreAlreadyExistException;
import com.restfulmovies.restfulmovies.exception.notfound.GenreNotFoundException;
import com.restfulmovies.restfulmovies.model.dto.genre.CreateGenreDto;
import com.restfulmovies.restfulmovies.model.dto.genre.GenreDto;
import com.restfulmovies.restfulmovies.model.entity.Genre;
import com.restfulmovies.restfulmovies.model.mapper.GenreMapper;
import com.restfulmovies.restfulmovies.model.repository.GenreRepository;
import com.restfulmovies.restfulmovies.service.impl.GenreServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceTest {

    @Mock
    private GenreRepository repository;

    @Mock
    private GenreMapper mapper;

    @InjectMocks
    private GenreServiceImpl service;

    @Test
    void findAll() {

        when(repository.findAll())
                .thenReturn(List.of(new Genre()));

        when(mapper.toDto(any(Genre.class)))
                .thenReturn(new GenreDto(1L, "name"));

        final var actual = service.findAll();

        assertThatCollection(actual)
                .isNotEmpty()
                .hasSize(1)
                .hasOnlyElementsOfType(GenreDto.class);

        verify(repository).findAll();
    }

    @Test
    void findOneShouldSucceed() {

        final var genre = new Genre("Action");
        final var expected = new GenreDto(1L, "Action");

        when(repository.findById(1L))
                .thenReturn(Optional.of(genre));

        when(mapper.toDto(repository.findById(1L).get()))
                .thenReturn(expected);

        final GenreDto actual = service.findOne(1L);

        assertThat(actual)
                .isNotNull()
                .isEqualTo(expected);


        verify(repository, times(2)).findById(1L);
        verify(mapper).toDto(any(Genre.class));
    }

    @Test
    void createShouldSucceed() {

        final var createDto = new CreateGenreDto("Action");
        final var genre = new Genre("Action");
        final var expected = new GenreDto(1L, "Action");


        when(repository.existsByName(any(String.class))).thenReturn(false);

        when(mapper.toEntity(any(CreateGenreDto.class)))
                .thenReturn(genre);

        when(repository.save(mapper.toEntity(createDto)))
                .then(returnsFirstArg());

        when(mapper.toDto(repository.save(mapper.toEntity(createDto))))
                .thenReturn(expected);

        final GenreDto actual = service.create(createDto);

        assertThat(actual)
                .isNotNull()
                .isEqualTo(expected);

        verify(repository).existsByName("Action");
        verify(mapper, times(3)).toEntity(createDto);
        verify(repository, times(2)).save(genre);
        verify(mapper).toDto(genre);
    }

    @Test
    void createShouldThrowAlreadyExistException() {

        when(repository.existsByName(any(String.class)))
                .thenReturn(true);

        assertThatExceptionOfType(GenreAlreadyExistException.class)
                .isThrownBy(() -> service.create(new CreateGenreDto("FAIL")));

        verify(repository).existsByName(any(String.class));
    }

    @Test
    void update() {
        final var updateDto = new CreateGenreDto("UPDATED");
        final var genre = new Genre("Action");
        final var expected = new GenreDto(1L, "UPDATED");

        when(repository.findById(1L)).thenReturn(Optional.of(genre));
        when(mapper.toDto(repository.save(mapper.update(updateDto, repository.findById(1L).get())))).thenReturn(expected);

        final GenreDto actual = service.update(1L, updateDto);

        assertThat(actual)
                .isNotNull()
                .isEqualTo(expected);
    }

    @Test
    void delete() {

        doNothing().when(repository).deleteById(any(Long.class));

        service.delete(1L);

        verify(repository).deleteById(1L);
    }
}