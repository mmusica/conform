package com.hub.form.model;

import lombok.Getter;
import lombok.Setter;

import com.hub.common.EntityEqualsAndHashCodeCommons;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "form_response")
public class FormResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Form form;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> answers;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Override
    public boolean equals(Object obj) {
        return EntityEqualsAndHashCodeCommons.equals(this, obj, getId(), ((Form) obj)::getId);
    }

    @Override
    public int hashCode() {
        return EntityEqualsAndHashCodeCommons.hashCode(this);
    }
}
