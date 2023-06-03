package com.restfulmovies.restfulmovies.model.mapper;

import com.restfulmovies.restfulmovies.model.entity.*;
import com.restfulmovies.restfulmovies.model.repository.*;
import jakarta.validation.Path;
import lombok.RequiredArgsConstructor;
import org.mapstruct.MapperConfig;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@MapperConfig
@RequiredArgsConstructor
public class BaseMapper {

    private final CountryRepository countryRepository;
    private final MovieInfoRepository movieInfoRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Named("findCountry")
    public Country findCountry(Long id) {
        return countryRepository.findById(id).orElseThrow();
    }

    @Named("findMovieInfo")
    public MovieInfo findMovieInfo(Long id) {
        return movieInfoRepository.findById(id).orElseThrow();
    }

    @Named("findMovieGenres")
    public Set<Genre> findMovieGenres(Set<Long> genresId) {
        if (genresId == null) {
            return  null;
        }

        Set<Genre> genres = genreRepository.findAllByIdIn(genresId);

        if (genres.isEmpty()) {
            throw new RuntimeException("Genres not found");
        }

        return genres;
    }

    @Named("findMovieActors")
    public Set<Actor> findMovieActors(Set<Long> actorsId) {
        if (actorsId == null) {
            return null;
        }

        Set<Actor> actors = actorRepository.findAllByIdIn(actorsId);

        if (actors.isEmpty()) {
            throw new RuntimeException("Actors not found");
        }

        return actors;
    }

    @Named("pathToString")
    public String pathToString(Path path) {
        return path.toString();
    }

    @Named("findUserRoles")
    public Set<UserRole> findUserRoles(Set<String> rolesStr) {
        return roleRepository.findAllByNameIn(rolesStr);
    }

    @Named("encodePassword")
    public String encodePassword(String raw) {
        return passwordEncoder.encode(raw);
    }
}
