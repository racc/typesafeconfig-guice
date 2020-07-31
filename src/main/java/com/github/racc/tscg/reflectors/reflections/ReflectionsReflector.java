package com.github.racc.tscg.reflectors.reflections;

import com.github.racc.tscg.Reflector;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

public class ReflectionsReflector implements Reflector {

    private final Reflections delegate;

    public ReflectionsReflector(Reflections delegate) {
        this.delegate = delegate;
    }

    @Override
    public Set<Constructor<?>> getConstructorsWithAnyParamAnnotated(Class clazz) {
        return delegate.getConstructorsWithAnyParamAnnotated(clazz);
    }

    @Override
    public Set<Method> getMethodsWithAnyParamAnnotated(Class clazz) {
        return delegate.getMethodsWithAnyParamAnnotated(clazz);
    }

    @Override
    public Set<Field> getFieldsAnnotatedWith(Class clazz) {
        return delegate.getFieldsAnnotatedWith(clazz);
    }
}
