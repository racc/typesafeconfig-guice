package com.github.racc.tscg.reflectors.classgraph;

import io.github.classgraph.FieldInfo;

public class FieldIdentifier {
    private final FieldInfo fieldInfo;

    public FieldIdentifier(FieldInfo fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public FieldInfo getFieldInfo() {
        return fieldInfo;
    }

    public String getClassName() {
        return fieldInfo.getClassInfo().getName();
    }

    public String getFieldName() {
        return fieldInfo.getName();
    }

    public boolean matchesField(FieldInfo field) {
        return field.compareTo(fieldInfo) == 0;
    }

}
