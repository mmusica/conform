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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "form")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "version", nullable = false)
    private Integer version;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<FormComponent> components;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_status_id")
    private FormStatus formStatus;

    @OneToMany(mappedBy = FormResponse_.FORM)
    private Set<FormResponse> responses;

    @Column(name = "created_at", nullable = false)
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
