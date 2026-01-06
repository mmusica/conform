package com.hub.mongo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hub.mongo.dto.FormDto;
import com.hub.mongo.model.FormComponent.HtmlElement;
import com.hub.util.FormCreator;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class FormServiceIT {

    @Inject
    FormService formService;

    @Test
    @TestTransaction
    @DisplayName("FormService - should persist a formDto and find by user")
    public void persistAndFind_validItem_shouldPersist() {

        var form = FormCreator.createValidForm();
        formService.save(form);
        List<FormDto> usersForms = formService.findUsersForms(form.getUser());

        assertThat(usersForms).isNotEmpty();
        assertThat(usersForms).extracting(uf -> uf.getUser()).allMatch(it -> it.equals(form.getUser()));
        assertThat(usersForms).extracting(uf -> uf.getComponents())
                .allSatisfy(compList -> assertThat(compList).allMatch((comp -> comp.getLabel().equals("label")
                        && comp.getType().equals(HtmlElement.TEXTAREA))));
    }
}
