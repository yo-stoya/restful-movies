package com.restfulmovies.restfulmovies.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "actors")
@Getter
@Setter
public class Actor extends BaseEntity{

    @NotBlank
    @Column(nullable = false, name = "first_name")
    private String firstName;

    @NotBlank
    @Column(nullable = false, name = "last_name")
    private String lastName;

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private LocalDate birthdate;

    @Positive
    private int height;

    private int awards;

    @ManyToOne
    private Country country;
}
