package com.restfulmovies.restfulmovies.service;


import com.restfulmovies.restfulmovies.model.dto.country.CountryDto;
import com.restfulmovies.restfulmovies.model.dto.country.CreateCountryDto;
import com.restfulmovies.restfulmovies.model.entity.Country;

import java.util.List;

public interface CountryService {
    List<CountryDto> findAll();

    CountryDto findOne(Long id);

    CountryDto create(CreateCountryDto dto);

    CountryDto update(Long id, CreateCountryDto dto);

    void delete(Long id);

//    Country findCountry(Long id);
}
