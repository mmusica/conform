package com.hub.form.model;

import lombok.Getter;
import lombok.Setter;

import com.hub.common.EntityEqualsAndHashCodeCommons;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

@Getter
@Setter
@Entity
@Table(name = "form_status")
@Audited(withModifiedFlag = true)
public class FormStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_At", nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "modified_at", nullable = false)
    @UpdateTimestamp
    private Instant modifiedAt;

    @Override
    public boolean equals(Object obj) {
        return EntityEqualsAndHashCodeCommons.equals(this, obj, getId(), ((Form) obj)::getId);
    }

    @Override
    public int hashCode() {
        return EntityEqualsAndHashCodeCommons.hashCode(this);
    }
}
