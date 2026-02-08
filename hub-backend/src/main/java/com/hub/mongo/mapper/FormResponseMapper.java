package com.hub.mongo.mapper;

import java.time.Instant;

import org.bson.types.ObjectId;

import com.hub.mongo.dto.FormResponseDto;
import com.hub.mongo.model.FormResponse;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FormResponseMapper {

    public FormResponse toEntity(FormResponseDto dto) {

        if (dto == null) {
            return null;
        }

        var result = new FormResponse();
        result.setUser(dto.getUser());
        result.setAnswers(dto.getAnswers());
        result.setFormId(new ObjectId(dto.getFormId()));
        result.setSubmittedAt(Instant.now());
        return result;
    }

}
