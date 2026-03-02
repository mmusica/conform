package com.hub.form.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionMapping;

@Getter
@Setter
@Entity(name = "UserRevision")
@Table(name = "user_rev_info")
@RevisionEntity(UserRevisionEntityListener.class)
public class UserRevision extends RevisionMapping {

    private Integer ppId;
    private String username;
}
