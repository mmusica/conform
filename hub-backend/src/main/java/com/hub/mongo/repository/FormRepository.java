package com.hub.mongo.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.hub.mongo.dto.FormDto;
import com.hub.mongo.model.Form;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FormRepository implements PanacheMongoRepository<Form> {

    public List<FormDto> findUsersForms(String user) {
        return find("user = :user", Parameters.with("user", user))
                .project(FormDto.class)
                .list();
    }

    public Optional<FormDto> findByIdOptionalDto(ObjectId id) {
        return find("id = :id", Parameters.with("id", id))
                .project(FormDto.class)
                .firstResultOptional();
    }
}
