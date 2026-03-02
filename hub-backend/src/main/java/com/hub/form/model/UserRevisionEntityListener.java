package com.hub.form.model;

import io.quarkus.security.identity.SecurityIdentity;
import lombok.NoArgsConstructor;

import jakarta.enterprise.inject.spi.CDI;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.envers.RevisionListener;

@NoArgsConstructor
public class UserRevisionEntityListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        Session session = CDI.current().select(Session.class).get();
        var userByDb = session.find(UserRevision.class, 1);
        Logger.getGlobal().log(Level.INFO, userByDb.getUsername());
        UserRevision user = (UserRevision) revisionEntity;
        // user.setUsername(CDI.current().select(SecurityIdentity.class).get().getPrincipal().getName());
        user.setUsername(userByDb.getUsername());
        user.setPpId(1);
    }

}
