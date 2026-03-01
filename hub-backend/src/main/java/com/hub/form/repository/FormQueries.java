package com.hub.form.repository;

import com.hub.form.model.Form;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.annotations.processing.Find;

public interface FormQueries {

    Session session();

    @Find
    public List<Form> find(String user$username);

}
