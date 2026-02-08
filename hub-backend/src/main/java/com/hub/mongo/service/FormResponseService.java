package com.hub.mongo.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.hub.mongo.dto.FormResponseDto;
import com.hub.mongo.mapper.FormResponseMapper;
import com.hub.mongo.repository.FormResponseRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class FormResponseService {

    final FormResponseRepository repository;
    final FormService formService;
    final FormResponseMapper mapper;

    @Transactional
    public void save(FormResponseDto formResponseDto) {
        formService.findById(formResponseDto.getFormId());
        repository.persist(mapper.toEntity(formResponseDto));
    }

    public List<FormResponseDto> findUserResponses(String user, String formId) {
        return repository.findUserResponses(user, new ObjectId(formId));
    }
}
