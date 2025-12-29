package com.hub.mongo.dto;

import java.text.Normalizer.Form;
import java.time.Instant;
import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hub.mongo.model.FormComponent;

import io.quarkus.mongodb.panache.common.ProjectionFor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ProjectionFor(Form.class)
public class FormDto {
    @JsonProperty(access = Access.READ_ONLY)
    private ObjectId id;

    @NotNull
    @Size(min = 1, max = 50)
    private String user;

    @JsonProperty(access = Access.READ_ONLY)
    private Instant createdAt;

    @Size(min = 1)
    private List<FormComponent> components;
}
