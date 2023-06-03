package com.restfulmovies.restfulmovies.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class Country extends BaseEntity{

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String continent;

    @NotBlank
    @Column(nullable = false)
    private String currency;
}
