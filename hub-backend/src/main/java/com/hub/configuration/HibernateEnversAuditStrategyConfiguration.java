package com.hub.configuration;

import io.quarkus.runtime.StartupEvent;
import lombok.RequiredArgsConstructor;

import jakarta.enterprise.event.Observes;

import org.hibernate.SessionFactory;

@RequiredArgsConstructor
public class HibernateEnversAuditStrategyConfiguration {

    // final SessionFactory sessionFactory;

    // void init (@Observes StartupEvent) {
    //     sessionFactory.bu
    // }

}
