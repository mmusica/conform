package com.hub.util;

import java.util.ArrayList;
import java.util.List;

import com.hub.mongo.dto.FormDto;
import com.hub.mongo.model.FormComponent;
import com.hub.mongo.model.FormComponent.HtmlElement;

public class FormCreator {

    public static FormDto createValidForm() {
        var result = new FormDto();
        result.setUser("user");
        result.setComponents(getComponents());
        result.setId("112345612345612345623456");
        return result;
    }

    public static List<FormComponent> getComponents() {
        List<FormComponent> result = new ArrayList<>();
        FormComponent formComponent = new FormComponent();
        formComponent.setLabel("label");
        formComponent.setName("name");
        formComponent.setType(HtmlElement.TEXTAREA);
        result.add(formComponent);
        return result;
    }
}
