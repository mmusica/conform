package com.hub.form.repository;

import com.hub.form.model.User;

import org.hibernate.Session;
import org.hibernate.annotations.processing.Find;

public interface UserQueries {

    Session session();

    @Find
    public User find(String username);
}
