package com.restfulmovies.restfulmovies.model.repository;

import com.restfulmovies.restfulmovies.model.entity.Actor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    Set<Actor> findAllByIdIn(@NotEmpty Set<Long> actorsId);

    boolean existsByFirstNameAndLastName(@NotBlank String firstName,
                                         @NotBlank String lastName);
}
