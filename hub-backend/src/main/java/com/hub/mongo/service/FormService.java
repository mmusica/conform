package com.hub.mongo.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.hub.mongo.dto.FormDto;
import com.hub.mongo.mapper.FormMapper;
import com.hub.mongo.repository.FormRepository;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class FormService {

    final FormMapper mapper;
    final FormRepository repository;

    public void save(FormDto dto) {
        repository.persist(mapper.toEntity(dto));
    }

    public List<FormDto> findUsersForms(String user) {
        return repository.findUsersForms(user);
    }

    public Optional<FormDto> find(ObjectId id) {
        return repository.findByIdOptionalDto(id);
    }

}
