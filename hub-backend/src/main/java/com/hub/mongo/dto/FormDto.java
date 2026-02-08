package com.hub.mongo.dto;

import java.time.Instant;
import java.util.List;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hub.mongo.model.Form;
import com.hub.mongo.model.FormComponent;

import io.quarkus.mongodb.panache.common.ProjectionFor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ProjectionFor(Form.class)
public class FormDto {

    @JsonProperty(access = Access.READ_ONLY)
    @BsonProperty("_id")
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;

    @NotBlank
    private String name;

    @NotNull
    @Size(min = 1, max = 50)
    private String user;

    @JsonProperty(access = Access.READ_ONLY)
    private Instant modifiedAt;

    @Size(min = 1)
    @NotNull
    private List<FormComponent> components;
}
