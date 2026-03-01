package com.hub.configuration;

import io.quarkus.hibernate.orm.JsonFormat;
import io.quarkus.hibernate.orm.PersistenceUnitExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.format.FormatMapper;

@JsonFormat
@PersistenceUnitExtension 
public class HibernateJSONFormateMapper implements FormatMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T fromString(CharSequence charSequence, JavaType<T> javaType, WrapperOptions wrapperOptions) {
        try {
            return objectMapper.readValue(charSequence.toString(),
                        objectMapper.constructType(javaType.getJavaType()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize JSON", e);
        }
    }

    @Override
    public <T> String toString(T value, JavaType<T> javaType, WrapperOptions wrapperOptions) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize to JSON", e);
        }
    }
}