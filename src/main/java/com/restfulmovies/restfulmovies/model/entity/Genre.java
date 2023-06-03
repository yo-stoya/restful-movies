package com.restfulmovies.restfulmovies.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "genres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genre extends BaseEntity{
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
}
