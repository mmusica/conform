package com.hub.mongo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;
import com.hub.common.EnumIdentifiable;

import lombok.Data;

@Data
public class FormComponent {
    public enum HtmlElement implements EnumIdentifiable<String> {
        TEXTAREA("textarea"),
        SELECT("select"),
        RADIO("radio");

        private String name;

        HtmlElement(String name) {
            this.name = name;
        }

        @JsonValue
        @Override
        public String getName() {
            return name;
        }
    }

    private String name;
    private HtmlElement type;
    private String label;
    private List<String> values;
}
