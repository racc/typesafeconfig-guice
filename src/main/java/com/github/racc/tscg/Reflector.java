package com.github.racc.tscg;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

public interface Reflector {
    Set<Constructor<?>> getConstructorsWithAnyParamAnnotated(Class<?> clazz);

    Set<Method> getMethodsWithAnyParamAnnotated(Class<?> clazz);

    Set<Field> getFieldsAnnotatedWith(Class<?> clazz);
}
