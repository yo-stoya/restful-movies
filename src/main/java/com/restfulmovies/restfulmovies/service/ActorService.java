package com.restfulmovies.restfulmovies.service;


import com.restfulmovies.restfulmovies.model.dto.actor.ActorDto;
import com.restfulmovies.restfulmovies.model.dto.actor.CreateActorDto;

import java.util.List;

public interface ActorService {
    List<ActorDto> findAll();

    ActorDto findOne(Long id);

    ActorDto create(CreateActorDto dto);

    ActorDto update(Long id, CreateActorDto dto);

    void delete(Long id);
}
