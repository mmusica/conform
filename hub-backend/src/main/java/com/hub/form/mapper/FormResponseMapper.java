package com.hub.form.mapper;

import lombok.RequiredArgsConstructor;

import com.hub.form.dto.FormResponseDto;
import com.hub.form.model.Form;
import com.hub.form.model.FormResponse;
import com.hub.form.model.User;

import jakarta.enterprise.context.ApplicationScoped;

import org.hibernate.Session;

@ApplicationScoped
@RequiredArgsConstructor
public class FormResponseMapper {

    final Session session;

    public FormResponse toEntity(FormResponseDto dto, User user) {

        if (dto == null) {
            return null;
        }

        var result = new FormResponse();
        result.setUser(user);
        result.setForm(session.getReference(Form.class, dto.getFormId()));
        result.setAnswers(dto.getAnswers());
        return result;
    }

}
