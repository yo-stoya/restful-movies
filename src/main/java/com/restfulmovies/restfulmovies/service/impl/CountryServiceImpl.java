package com.restfulmovies.restfulmovies.service.impl;

import com.restfulmovies.restfulmovies.exception.alreadyexist.CountryAlreadyExistException;
import com.restfulmovies.restfulmovies.exception.notfound.CountryNotFoundException;
import com.restfulmovies.restfulmovies.exception.notfound.GenreNotFoundException;
import com.restfulmovies.restfulmovies.model.dto.country.CountryDto;
import com.restfulmovies.restfulmovies.model.dto.country.CreateCountryDto;
import com.restfulmovies.restfulmovies.model.entity.Country;
import com.restfulmovies.restfulmovies.model.mapper.CountyMapper;
import com.restfulmovies.restfulmovies.model.repository.CountryRepository;
import com.restfulmovies.restfulmovies.service.CountryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountyMapper countyMapper;

    private Country findCountry(Long id) {
        return countryRepository.findById(id).orElseThrow(() ->
                new CountryNotFoundException(String.valueOf(id)));
    }

    @Override
    public List<CountryDto> findAll() {
        return countryRepository.findAll()
                .stream()
                .map(countyMapper::toDto)
                .toList();
    }

    @Override
    public CountryDto findOne(Long id) {
        return countyMapper.toDto(findCountry(id));
    }

    @Override
    public CountryDto create(CreateCountryDto dto) {
        if (countryRepository.existsByName(dto.name())) {
            throw new CountryAlreadyExistException(dto.name());
        }

        var saved = countryRepository.save(countyMapper.toEntity(dto));
        return countyMapper.toDto(saved);
    }

    @Override
    @Transactional
    public CountryDto update(Long id, CreateCountryDto dto) {
        var updated = countryRepository.save(countyMapper.update(dto, findCountry(id)));
        return countyMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
