package com.github.racc.tscg.reflectors.classgraph;

import com.github.racc.tscg.Reflector;
import io.github.classgraph.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class ClassGraphReflector implements Reflector {
    private final ScanResult scanResult;

    public ClassGraphReflector(String... scannerSpec) {
        scanResult = new ClassGraph()
                .acceptPackages(scannerSpec)
                .enableAllInfo()
                .ignoreClassVisibility()
                .ignoreFieldVisibility()
                .ignoreMethodVisibility()
                .scan();
    }

    @Override
    public Set<Constructor<?>> getConstructorsWithAnyParamAnnotated(Class<?> clazz) {
        final Set<MethodIdentifier> methodIdentifiers = new HashSet<>();
        final ClassInfoList infoList = scanResult.getClassesWithMethodParameterAnnotation(clazz.getName());

        infoList.forEach(classInfo -> {
            final MethodInfoList methodInfos = classInfo.getConstructorInfo();
            methodInfos.forEach(methodInfo -> {
                if (!methodInfo.isConstructor()) {
                    return;
                }

                methodIdentifiers.add(new MethodIdentifier(methodInfo));
            });
        });

        final Set<Constructor<?>> constructors = new HashSet<>(methodIdentifiers.size());
        methodIdentifiers.forEach(methodIdentifier -> {
            final ClassInfo classInfo = scanResult.getClassInfo(methodIdentifier.getClassName());
            classInfo.getConstructorInfo().forEach(methodInfo -> {
                if (methodInfo.isConstructor() && methodIdentifier.matchesMethod(methodInfo)) {
                    constructors.add(methodInfo.loadClassAndGetConstructor());
                }
            });
        });

        return constructors;
    }

    @Override
    public Set<Method> getMethodsWithAnyParamAnnotated(Class<?> clazz) {
        final Set<MethodIdentifier> methodIdentifiers = new HashSet<>();
        final ClassInfoList infoList = scanResult.getClassesWithMethodParameterAnnotation(clazz.getName());

        infoList.forEach(classInfo -> {
            final MethodInfoList methodInfos = classInfo.getMethodInfo();
            methodInfos.forEach(methodInfo -> methodIdentifiers.add(new MethodIdentifier(methodInfo)));
        });

        final Set<Method> methods = new HashSet<>(methodIdentifiers.size());
        methodIdentifiers.forEach(methodIdentifier -> {
            final ClassInfo classInfo = scanResult.getClassInfo(methodIdentifier.getClassName());
            classInfo.getMethodInfo().forEach(methodInfo -> {
                if (methodIdentifier.matchesMethod(methodInfo)) {
                    methods.add(methodInfo.loadClassAndGetMethod());
                }
            });
        });

        return methods;
    }

    @Override
    public Set<Field> getFieldsAnnotatedWith(Class<?> clazz) {
        final Set<FieldIdentifier> fieldIdentifiers = new HashSet<>();
        final ClassInfoList infoList = scanResult.getClassesWithFieldAnnotation(clazz.getName());

        infoList.forEach(classInfo -> {
            final FieldInfoList fieldInfoList = classInfo.getDeclaredFieldInfo();
            fieldInfoList.forEach(fieldInfo -> fieldIdentifiers.add(new FieldIdentifier(fieldInfo)));
        });

        final Set<Field> fields = new HashSet<>(fieldIdentifiers.size());
        fieldIdentifiers.forEach(fieldIdentifier -> {
            final ClassInfo classInfo = scanResult.getClassInfo(fieldIdentifier.getClassName());
            classInfo.getDeclaredFieldInfo().forEach(fieldInfo -> {
                if (fieldIdentifier.matchesField(fieldInfo)) {
                    fields.add(fieldInfo.loadClassAndGetField());
                }
            });
        });
        return fields;
    }

}
