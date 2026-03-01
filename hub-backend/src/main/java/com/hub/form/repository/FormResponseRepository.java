package com.hub.form.repository;

import com.hub.form.dto.FormResponseDto;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class FormResponseRepository {

    // public List<FormResponseDto> findUserResponses(String user) {
        // return find("user = :user and formId = :formId ",
        //         Parameters.with("user", user)
        //                 .and("formId", formId))
        //         .project(FormResponseDto.class).list();
    // }
}
