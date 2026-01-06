package com.hub.common;

import java.util.Arrays;

public interface EnumIdentifiable<T> {

    public static <T, K extends Enum<K> & EnumIdentifiable<T>> K findByName(Class<K> enumClass, T name) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(it -> it.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "No enum constant for " + enumClass.getSimpleName() + " with name " + name));

    }

    T getName();
}
