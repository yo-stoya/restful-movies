package com.restfulmovies.restfulmovies.model.mapper;

import com.restfulmovies.restfulmovies.model.dto.country.CountryDto;
import com.restfulmovies.restfulmovies.model.dto.country.CreateCountryDto;
import com.restfulmovies.restfulmovies.model.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CountyMapper {
    @Mapping(target = "id", ignore = true)
    Country toEntity(CreateCountryDto dto);

    CountryDto toDto(Country country);

    @Mapping(target = "id", ignore = true)
    Country update(CreateCountryDto dto, @MappingTarget Country country);
}
