package com.restfulmovies.restfulmovies.service;

import com.restfulmovies.restfulmovies.exception.notfound.CountryNotFoundException;
import com.restfulmovies.restfulmovies.model.dto.country.CountryDto;
import com.restfulmovies.restfulmovies.model.dto.country.CreateCountryDto;
import com.restfulmovies.restfulmovies.model.entity.Country;
import com.restfulmovies.restfulmovies.model.mapper.CountyMapper;
import com.restfulmovies.restfulmovies.model.repository.CountryRepository;
import com.restfulmovies.restfulmovies.service.impl.CountryServiceImpl;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Sql("/scripts/init-countries.sql")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountyMapper countyMapper;

    @Test
    @Order(2)
    void findAll() {
        final List<CountryDto> actual = countryService.findAll();

        assertThatCollection(actual).hasSize(3);
        assertThatCollection(actual).hasOnlyElementsOfType(CountryDto.class);
        assertThat(actual.get(0).name()).isEqualTo("Albania");
        assertThat(actual.get(1).name()).isEqualTo("Argentina");
        assertThat(actual.get(2).name()).isEqualTo("Armenia");
    }

//    @Test
//    void findCountry() {
//        final var country = Optional.of(countryService.findCountry(1L));
//        assertThat(country.get().getName()).isEqualTo("Albania");
//        assertThat(country.get().getContinent()).isEqualTo("Europe");
//    }
//
//    @Test
//    void findCountryException() {
//        assertThatExceptionOfType(CountryNotFoundException.class)
//                .isThrownBy(() -> countryService.findCountry(99L));
//    }

    @Test
    @Order(1)
    void findOne() throws NoSuchMethodException {
//        final CountryDto expected = new CountryDto(1L, "Albania", "Europe", "ALL");
        final CountryDto actual = countryService.findOne(1L);

        final Method findCountry = CountryServiceImpl.class.getDeclaredMethod("findCountry", Long.class);
        findCountry.setAccessible(true);


        assertThat(actual.name()).isEqualTo("Albania");
        assertThat(actual.continent()).isEqualTo("Europe");
        assertThat(actual.currency()).isEqualTo("ALL");
    }

    @Test
//    @Order(3)
    void create() {

        final CreateCountryDto createDto = new CreateCountryDto(
                "New Country",
                "New Continent",
                "New Currency");
        final CountryDto actual = countryService.create(createDto);

        assertThat(actual).isNotNull();
        assertThat(actual.name()).isEqualTo(createDto.name());
        assertThat(actual.continent()).isEqualTo(createDto.continent());
        assertThat(actual.currency()).isEqualTo(createDto.currency());
    }

    @Test
//    @Order(5)
    void update() {

        final CreateCountryDto updateDto = new CreateCountryDto(
                "Update Country",
                "Update Continent",
                "Update Currency");

//        final CountryDto expected = countyMapper.toDto(countyMapper.toEntity(updateDto));

        final CountryDto actual = countryService.update(1L, updateDto);

        assertThat(actual.name()).isEqualTo(updateDto.name());
        assertThat(actual.continent()).isEqualTo(updateDto.continent());

    }

    @Test
//    @Order(4)
    void delete() {
        countryService.delete(4L);

        final List<CountryDto> remaining = countryService.findAll();

        assertThatCollection(remaining).hasSize(3);
        assertThat(remaining.get(0).name()).isEqualTo("Albania");
        assertThat(remaining.get(1).name()).isEqualTo("Argentina");
        assertThat(remaining.get(2).name()).isEqualTo("Armenia");

    }
}