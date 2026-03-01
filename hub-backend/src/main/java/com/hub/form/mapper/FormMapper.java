package com.hub.form.mapper;
import lombok.RequiredArgsConstructor;

import com.hub.form.dto.FormDto;
import com.hub.form.model.Form;
import com.hub.form.model.FormStatus;
import com.hub.form.model.User;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;

import org.hibernate.Session;

@ApplicationScoped
@RequiredArgsConstructor
public class FormMapper {

    final Session session;

    public Form toEntity(FormDto dto) {
        if (dto == null) {
            return null;
        }

        Form result = new Form();
        result.setName(dto.getName());
        result.setUser(session.getReference(User.class, 1));
        result.setComponents(dto.getComponents());
        result.setCreatedAt(Instant.now());
        result.setVersion(1);
        result.setFormStatus(session.getReference(FormStatus.class, 1));
        return result;
    }

    public FormDto toDto(Form entity) {
        if (entity == null) {
            return null;
        }

        FormDto result = new FormDto();
        result.setComponents(entity.getComponents());
        result.setId(entity.getId());
        result.setModifiedAt(entity.getModifiedAt());
        return result;
    }
}
