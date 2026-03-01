package com.hub.form.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.Map;

@Data
public class FormResponseDto {

    @NotNull
    private Integer formId;

    @JsonProperty(access = Access.READ_ONLY)
    private String user;

    @JsonProperty(access = Access.READ_ONLY)
    private Instant submittedAt;

    @Size(min = 1)
    @NotNull
    private Map<String, String> answers;
}
