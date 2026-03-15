package com.hub.form.repository;

import com.hub.form.model.Form;
import com.hub.form.model.FormElement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.annotations.processing.Find;
import org.hibernate.annotations.processing.HQL;

public interface FormQueries {

    Session session();

    @Find
    public List<Form> find(String user$username);

    @HQL("select fe from FormElement fe")
    public List<FormElement> findAllElements();

}
