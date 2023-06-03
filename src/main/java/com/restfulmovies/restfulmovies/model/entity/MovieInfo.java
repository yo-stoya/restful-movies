package com.restfulmovies.restfulmovies.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "movies_info")
@Getter
@Setter
public class MovieInfo extends BaseEntity{

    @NotNull
    @Positive
    @Column(nullable = false)
    private int rating;

    @NotNull
    @Positive
    @Column(nullable = false)
    private int runtime;

    @NotBlank
    @Column(nullable = false)
    private String thumbnail;

    @Positive
    private BigDecimal budget;

    @NotNull
    @Column(nullable = false, name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "has_subtitles")
    private boolean hasSubtitles;

    @Column(length = 1000)
    private String description;
}
