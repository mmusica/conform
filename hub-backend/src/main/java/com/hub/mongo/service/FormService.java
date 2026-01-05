package com.hub.mongo.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.hub.mongo.dto.FormDto;
import com.hub.mongo.mapper.FormMapper;
import com.hub.mongo.repository.FormRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class FormService {

    final FormMapper mapper;
    final FormRepository repository;

    public void persist(FormDto dto) {
        repository.persist(mapper.toEntity(dto));
    }

    public List<FormDto> findUsersForms(String user) {
        List<FormDto> usersForms = repository.findUsersForms(user);
        return usersForms;
    }

    public Optional<FormDto> find(ObjectId id) {
        return repository.findByIdOptionalDto(id);
    }

    public FormDto findById(String id) {
        return find(new ObjectId(id)).orElseThrow(() -> new NotFoundException("Form with id %s does not exist"));
    }
}
