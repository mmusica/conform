package com.hub.form.service;

import lombok.RequiredArgsConstructor;

import com.hub.form.dto.FormDto;
import com.hub.form.mapper.FormMapper;
import com.hub.form.model.Form;
import com.hub.form.repository.FormQueries;
import com.hub.form.repository.FormQueries_;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;

@ApplicationScoped
@RequiredArgsConstructor
public class FormService {

    final FormMapper mapper;
    final Session session;

    @Transactional
    public void save(FormDto dto) {
        session.persist(mapper.toEntity(dto));
        // repository.persist(mapper.toEntity(dto));
    }

    public List<FormDto> findUsersForms(String user) {
        return new FormQueries_(session)
                .find(user)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public Optional<FormDto> find() {
        // return repository.findByIdOptionalDto(id);
        return null;
    }

    public FormDto findById(Integer id) {
        return Optional.ofNullable(session.find(Form.class, id))
                .map(mapper::toDto)
                .orElse(null);
    }
}
