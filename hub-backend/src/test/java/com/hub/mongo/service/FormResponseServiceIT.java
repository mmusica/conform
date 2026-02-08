package com.hub.mongo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hub.mongo.dto.FormDto;
import com.hub.mongo.dto.FormResponseDto;
import com.hub.util.FormCreator;

import io.quarkus.test.InjectMock;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

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

        when(formService.findById(formResponseDto.getFormId())).thenReturn(formDto);
        formResponseService.save(formResponseDto);
        var found = formResponseService.findUserResponses(formResponseDto.getUser(),
                formResponseDto.getFormId());

        assertThat(found).isNotNull()
                .isNotEmpty()
                .allSatisfy(it -> {
                    assertThat(it.getFormId()).isEqualTo(formResponseDto.getFormId());
                    assertThat(it.getAnswers()).containsExactlyEntriesOf(formResponseDto.getAnswers());
                });
    }

    private FormResponseDto getFormResponseDto() {
        FormResponseDto formResponseDto = new FormResponseDto();
        formResponseDto.setUser("user");
        formResponseDto.setAnswers(Map.of("question", "answer"));
        formResponseDto.setFormId("69512e7fcb16061d4aff8520");
        return formResponseDto;
    }
}
