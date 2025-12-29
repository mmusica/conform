package com.hub.mongo.repository;

import java.util.List;

import org.bson.types.ObjectId;

import com.hub.mongo.dto.FormResponseDto;
import com.hub.mongo.model.FormResponse;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FormResponseRepository implements PanacheMongoRepository<FormResponse> {

    public List<FormResponseDto> findUserResponses(String user, ObjectId formId) {
        return find("user = :user and formId = :formId ",
                Parameters.with("user", user)
                        .and("formId", formId))
                .project(FormResponseDto.class).list();
    }
}
