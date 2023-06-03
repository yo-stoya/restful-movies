package com.restfulmovies.restfulmovies.model.mapper;

import com.restfulmovies.restfulmovies.model.dto.user.UserDto;
import com.restfulmovies.restfulmovies.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface UserMapper {

    @Mapping(target = "jwtTokens", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", source = "roles", qualifiedByName = "findUserRoles")
    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    User toEntity(UserDto dto);
}
