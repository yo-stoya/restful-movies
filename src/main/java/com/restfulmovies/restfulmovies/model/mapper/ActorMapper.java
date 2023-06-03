package com.restfulmovies.restfulmovies.model.mapper;

import com.restfulmovies.restfulmovies.model.dto.actor.ActorDto;
import com.restfulmovies.restfulmovies.model.dto.actor.CreateActorDto;
import com.restfulmovies.restfulmovies.model.entity.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface ActorMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", source = "country", qualifiedByName = "findCountry")
    Actor toEntity(CreateActorDto dto);

    ActorDto toDto(Actor actor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", source = "country", qualifiedByName = "findCountry")
    Actor update(CreateActorDto dto, @MappingTarget Actor actor);
}
