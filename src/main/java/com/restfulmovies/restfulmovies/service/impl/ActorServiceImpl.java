package com.restfulmovies.restfulmovies.service.impl;

import com.restfulmovies.restfulmovies.exception.alreadyexist.ActorAlreadyExistException;
import com.restfulmovies.restfulmovies.exception.notfound.ActorNotFoundException;
import com.restfulmovies.restfulmovies.model.dto.actor.ActorDto;
import com.restfulmovies.restfulmovies.model.dto.actor.CreateActorDto;
import com.restfulmovies.restfulmovies.model.entity.Actor;
import com.restfulmovies.restfulmovies.model.mapper.ActorMapper;
import com.restfulmovies.restfulmovies.model.repository.ActorRepository;
import com.restfulmovies.restfulmovies.service.ActorService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    private Actor findActor(Long id) {
        return actorRepository.findById(id).orElseThrow(() -> new ActorNotFoundException(String.valueOf(id)));
    }

    @Override
    public List<ActorDto> findAll() {
        return actorRepository
                .findAll()
                .stream()
                .map(actorMapper::toDto)
                .toList();
    }

    @Override
    public ActorDto findOne(Long id) {
        return actorMapper.toDto(findActor(id));
    }

    @Override
    public ActorDto create(CreateActorDto dto) {
        if (actorRepository.existsByFirstNameAndLastName(dto.firstName(), dto.lastName())) {
            throw new ActorAlreadyExistException(dto.firstName() + dto.lastName());
        }

        var saved = actorRepository.save(actorMapper.toEntity(dto));
        return actorMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ActorDto update(Long id, CreateActorDto dto) {
        var updated = actorRepository.save(actorMapper.update(dto, findActor(id)));
        return actorMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        actorRepository.deleteById(id);
    }
}
