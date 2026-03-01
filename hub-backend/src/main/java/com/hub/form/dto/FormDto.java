package com.hub.form.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hub.form.model.FormComponent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormDto {

    private Integer id;

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
