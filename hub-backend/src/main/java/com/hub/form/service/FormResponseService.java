package com.hub.form.service;

import lombok.RequiredArgsConstructor;

import com.hub.form.dto.FormResponseDto;
import com.hub.form.mapper.FormResponseMapper;
import com.hub.form.model.User;
import com.hub.form.repository.UserQueries_;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

import org.hibernate.Session;

@ApplicationScoped
@RequiredArgsConstructor
public class FormResponseService {

    final FormService formService;
    final FormResponseMapper mapper;
    final Session session;

    @Transactional
    public void save(FormResponseDto formResponseDto, String username) {
        User user = new UserQueries_(session).find(username);       
        formService.findById(formResponseDto.getFormId());
        session.persist(mapper.toEntity(formResponseDto, user));
    }

    public List<FormResponseDto> findUserResponses(String user, String formId) {
        // return repository.findUserResponses(user, new ObjectId(formId));
        return List.of();
    }
}
