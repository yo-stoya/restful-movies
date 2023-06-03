package com.restfulmovies.restfulmovies.model.repository;

import com.restfulmovies.restfulmovies.model.entity.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Set<Genre> findAllByIdIn(@NotEmpty Set<Long> ids);

    boolean existsByName(@NotBlank String name);
}
