package com.restfulmovies.restfulmovies.model.repository;

import com.restfulmovies.restfulmovies.model.entity.Genre;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCollection;


@DataJpaTest
@ActiveProfiles("test")
@Sql("/scripts/init-genres.sql")
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void findAllByIdIn() {
        var actual = new ArrayList<>(genreRepository.findAllByIdIn(Set.of(1L, 2L)));

        assertThatCollection(actual).isNotEmpty()
                                    .hasSize(2);

        assertThat(actual.get(0).getName()).isEqualTo("Action");
        assertThat(actual.get(1).getName()).isEqualTo("Adventure");
    }

    @Test
    void existsByName() {
        assertThat(genreRepository.existsByName("Action")).isTrue();
    }
}