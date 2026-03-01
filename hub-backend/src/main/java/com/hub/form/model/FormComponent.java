package com.hub.form.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FormComponent implements Serializable {
    private String label;
    private String element;
    private List<String> values;
}
