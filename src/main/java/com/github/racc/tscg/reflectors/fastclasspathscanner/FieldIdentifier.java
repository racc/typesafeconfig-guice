package com.github.racc.tscg.reflectors.fastclasspathscanner;

import java.lang.reflect.Field;

public class FieldIdentifier {
    private final String className;
    private final String fieldName;
    private final Class<?> fieldType;

    public FieldIdentifier(String className, String fieldName,  Class<?> fieldType) {
        this.className = className;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public String getClassName() {
        return className;
    }

    public String getFieldName() {
        return fieldName;
    }

    public  Class<?> getFieldType() {
        return fieldType;
    }

    public boolean matchesField(Class<?> clazz, Field field) {
       return (clazz.getCanonicalName().equals(className) && field.getName().equals(fieldName) && field.getType() == fieldType);
    }

}
