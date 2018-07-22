package com.github.racc.tscg;

import java.io.Serializable;
import java.lang.annotation.Annotation;

/**
 * Inspired in Guice's NamedImpl.
 */
public class TypesafeConfigImpl implements TypesafeConfig, Serializable {
    private final String value;

    public TypesafeConfigImpl(String forKeypath) {
        this.value = forKeypath;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return (127 * "value".hashCode()) ^ value.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TypesafeConfig)) {
            return false;
        }

        TypesafeConfig other = (TypesafeConfig) o;
        return value.equals(other.value());
    }

    @Override
    public String toString() {
        return "@" + TypesafeConfig.class.getName() + "(value=" + value + ")";
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return TypesafeConfig.class;
    }

    private static final long serialVersionUID = 0;
}
