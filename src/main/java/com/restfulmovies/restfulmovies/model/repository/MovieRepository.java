package com.restfulmovies.restfulmovies.model.repository;

import com.restfulmovies.restfulmovies.model.entity.Movie;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTitle(@NotBlank String title);
}
