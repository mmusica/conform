package com.hub.form.model;

import io.quarkus.security.identity.SecurityIdentity;
import lombok.NoArgsConstructor;

import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.envers.RevisionListener;

@NoArgsConstructor
public class UserRevisionEntityListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        Session session = CDI.current().select(Session.class).get();
        var sessionUsername = CDI.current().select(SecurityIdentity.class).get().getPrincipal().getName();

        var builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        query.where(builder.equal(root.get(User_.USERNAME), sessionUsername));
        User dbUser = session.createQuery(query).getSingleResultOrNull();

        UserRevision ur = (UserRevision) revisionEntity;
        ur.setUser(dbUser);
        ur.setPpId(1);



        // UserRevision user = (UserRevision) revisionEntity;
        // user.setUsername(CDI.current().select(SecurityIdentity.class).get().getPrincipal().getName());
        // user.setUsername(userByDb.getUsername());
        // user.setPpId(1);
    }

}
