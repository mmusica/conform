package com.hub.mongo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hub.mongo.dto.FormDto;
import com.hub.mongo.dto.FormResponseDto;
import com.hub.util.FormCreator;

import io.quarkus.test.InjectMock;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@QuarkusTest
class FormResponseServiceIT {

    @Inject
    FormResponseService formResponseService;

    @InjectMock
    FormService formService;

    @Test
    @TestTransaction
    @DisplayName("FormResponseService - should persist a formResponse and find by user")
    void persistAndFind_validItem_shouldPersist() {

        FormResponseDto formResponseDto = getFormResponseDto();
        FormDto formDto = FormCreator.createValidForm();

        when(formService.find(formResponseDto.getFormId())).thenReturn(Optional.of(formDto));
        formResponseService.save(formResponseDto);
        var found = formResponseService.findUserResponses(formResponseDto.getUser(),
                formResponseDto.getFormId().toHexString());

        assertThat(found).isNotNull().isNotEmpty();

        assertThat(found).allSatisfy(it -> assertThat(it.getAnswers())
                .allSatisfy((k, v) -> assertThat(formResponseDto.getAnswers().get(k)).isEqualTo(v)));
        assertThat(found).allSatisfy(it -> it.getFormId().equals(formDto.getId()));
    }

    @Test
    @TestTransaction
    @DisplayName("FormResponseService - should throw NotFoundException when no form exists")
    void persistAndFind_notFoundForm_shouldThrowNotFound() {
        FormResponseDto formResponseDto = getFormResponseDto();
        when(formService.find(formResponseDto.getFormId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> formResponseService.save(formResponseDto)).isInstanceOf(NotFoundException.class);
    }

    private FormResponseDto getFormResponseDto() {
        FormResponseDto formResponseDto = new FormResponseDto();
        formResponseDto.setUser("user");
        formResponseDto.setAnswers(Map.of("question", "answer"));
        formResponseDto.setFormId(new ObjectId());
        return formResponseDto;
    }
}
