package com.hub.form.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionMapping;

@Getter
@Setter
@Entity(name = "UserRevision")
@Table(name = "user_rev_info")
@RevisionEntity(UserRevisionEntityListener.class)
public class UserRevision extends RevisionMapping {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "pp_id")
    private Integer ppId;
}
