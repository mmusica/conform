package com.hub.mongo.model;

import java.time.Instant;
import java.util.List;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MongoEntity(collection = "Form")
public class Form extends PanacheMongoEntity {
    private String name;
    private String user;
    private Instant createdAt;
    private List<FormComponent> components;
}
