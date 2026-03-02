package com.hub.form.model;

import io.quarkus.security.identity.SecurityIdentity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.hibernate.envers.RevisionListener;

@NoArgsConstructor
@AllArgsConstructor
public class UserRevisionEntityListener implements RevisionListener {

    SecurityIdentity identity;

    @Override
    public void newRevision(Object revisionEntity) {
        UserRevision user = (UserRevision) revisionEntity;
        user.setUsername(identity.getPrincipal().getName());
        user.setPpId(1);
    }

}
