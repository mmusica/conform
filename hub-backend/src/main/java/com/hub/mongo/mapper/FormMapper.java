package com.hub.mongo.mapper;

import java.time.Instant;

import com.hub.mongo.dto.FormDto;
import com.hub.mongo.model.Form;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FormMapper {

    public Form toEntity(FormDto dto) {
        if (dto == null) {
            return null;
        }

        Form result = new Form();
        result.setName(dto.getName());
        result.setUser(dto.getUser());
        result.setComponents(dto.getComponents());
        result.setCreatedAt(Instant.now());
        return result;
    }
}
