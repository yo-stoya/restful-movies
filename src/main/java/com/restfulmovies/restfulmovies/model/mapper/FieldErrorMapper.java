package com.restfulmovies.restfulmovies.model.mapper;

import com.restfulmovies.restfulmovies.model.exception.InvalidFieldDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.validation.FieldError;

@Mapper(componentModel = "spring")
public interface FieldErrorMapper {
    @Mapping(target = "property", source = "field")
    @Mapping(target = "message", source = "defaultMessage")
    InvalidFieldDto toDto(FieldError objectError);

}
