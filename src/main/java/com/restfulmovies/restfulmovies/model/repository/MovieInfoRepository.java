package com.restfulmovies.restfulmovies.model.repository;

import com.restfulmovies.restfulmovies.model.entity.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieInfoRepository extends JpaRepository<MovieInfo, Long> {
}
