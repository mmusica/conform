package com.hub.common;

import java.util.Objects;
import java.util.function.Supplier;

import org.hibernate.proxy.HibernateProxy;

public final class EntityEqualsAndHashCodeCommons {

    private EntityEqualsAndHashCodeCommons() {
        // util class
    }

    public static boolean equals(Object thisObject, Object o, Number thisId, Supplier<Number> thatIdSupplier) {

        if (thisObject == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        Class<?> oEffectiveClass = HibernateProxy.extractLazyInitializer(o) != null
                ? HibernateProxy.extractLazyInitializer(o).getPersistentClass()
                : o.getClass();

        Class<?> thisObjectEffectiveClass = HibernateProxy.extractLazyInitializer(thisObject) != null
                ? HibernateProxy.extractLazyInitializer(thisObject).getPersistentClass()
                : thisObject.getClass();

        if (oEffectiveClass != thisObjectEffectiveClass) {
            return false;
        }
        return thisId != null && Objects.equals(thisId, thatIdSupplier.get());
    }

    public static int hashCode(Object thisObject) {
        return HibernateProxy.extractLazyInitializer(thisObject) != null
                ? HibernateProxy.extractLazyInitializer(thisObject).getPersistentClass().hashCode()
                : thisObject.getClass().hashCode();
    }
}
