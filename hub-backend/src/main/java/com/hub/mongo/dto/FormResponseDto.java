package com.hub.mongo.dto;

import java.time.Instant;
import java.util.Map;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hub.mongo.model.FormResponse;

import io.quarkus.mongodb.panache.common.ProjectionFor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@ProjectionFor(FormResponse.class)
public class FormResponseDto {

    @NotNull
    @BsonProperty("formId")
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String formId;

    @NotNull
    @Size(min = 1, max = 50)
    private String user;

    @JsonProperty(access = Access.READ_ONLY)
    private Instant submittedAt;

    @Size(min = 1)
    @NotNull
    private Map<String, Object> answers;
}
