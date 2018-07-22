package com.github.racc.tscg.reflectors.fastclasspathscanner;

import com.github.racc.tscg.Reflector;
import com.github.racc.tscg.TypesafeConfig;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class FastClasspathScanningReflector implements Reflector {

    private final ScanResult scanResult;
    private final FastClasspathScanner scanner;

    public FastClasspathScanningReflector(String ...scannerSpec) {
        this.scanner = new FastClasspathScanner(scannerSpec).enableMethodInfo().enableFieldInfo().ignoreFieldVisibility().ignoreMethodVisibility();
        this.scanResult = scanner.scan();
    }

    @Override
    public Set<Constructor> getConstructorsWithAnyParamAnnotated(Class clazz) {
        Map<String, ClassInfo> classInfos = scanResult.getClassNameToClassInfo();
        Set<MethodIdentifier> methodIdentifiers = new HashSet<>();
        for (ClassInfo classInfo : classInfos.values()){
            for (MethodInfo methodInfo : classInfo.getMethodAndConstructorInfo()) {
                if (methodInfo.isConstructor()) {
                        AnnotationInfo[][] parametersAnnotationInfo = methodInfo.getParameterAnnotationInfo();
                        if (parametersAnnotationInfo != null)
                        for (AnnotationInfo[] oneParametersAnnotationInfo: parametersAnnotationInfo) {
                            for (AnnotationInfo ai : oneParametersAnnotationInfo) {
                                if (ai.getAnnotationType() == clazz) {
                                    methodIdentifiers.add(new MethodIdentifier(methodInfo.getClassName(), methodInfo.getMethodName(), methodInfo.getParameterTypeSignatures()));
                                }
                            }
                        }
                    }
                }
            }
            Set<Constructor> constructors = new HashSet<>(methodIdentifiers.size());
        for (MethodIdentifier methodIdentifier : methodIdentifiers)
        {
            Class<?> classe = scanResult.classNameToClassRef(methodIdentifier.getClassName());
            for (Constructor<?> constructor : classe.getConstructors()) {
                if (methodIdentifier.matchesConstructor(classe, constructor, scanResult)){
                    constructors.add(constructor);
                }
            }

        }
        return constructors;
    }

    @Override
    public Set<Method> getMethodsWithAnyParamAnnotated(Class clazz) {
        Map<String, ClassInfo> classInfos = scanResult.getClassNameToClassInfo();
        Set<MethodIdentifier> methodIdentifiers = new HashSet<>();
        for (ClassInfo classInfo : classInfos.values()){
            for (MethodInfo methodInfo : classInfo.getMethodAndConstructorInfo()) {
                    AnnotationInfo[][] parametersAnnotationInfo = methodInfo.getParameterAnnotationInfo();
                    if (parametersAnnotationInfo != null)
                        for (AnnotationInfo[] oneParametersAnnotationInfo: parametersAnnotationInfo) {
                            for (AnnotationInfo ai : oneParametersAnnotationInfo) {
                                if (ai.getAnnotationType() == clazz) {
                                    methodIdentifiers.add(new MethodIdentifier(methodInfo.getClassName(), methodInfo.getMethodName(), methodInfo.getParameterTypeSignatures()));
                                }
                            }
                        }
                }
        }
        Set<Method> methods = new HashSet<>(methodIdentifiers.size());
        for (MethodIdentifier methodIdentifier : methodIdentifiers) {
            Class<?> classe = scanResult.classNameToClassRef(methodIdentifier.getClassName());
            for (Method method : classe.getDeclaredMethods()) {
                if (methodIdentifier.matchesMethod(classe, method, scanResult)){
                    methods.add(method);
                }
            }
        }
        return methods;
    }

    @Override
    public Set<Field> getFieldsAnnotatedWith(Class clazz) {
        Map<String, ClassInfo> classInfos = scanResult.getClassNameToClassInfo();
        Set<FieldIdentifier> fieldIdentifiers = new HashSet<>();
        for (ClassInfo classInfo : classInfos.values()){
            for (FieldInfo fieldInfo : classInfo.getFieldInfo()) {
                    List<AnnotationInfo> fieldsAnnotationInfos = fieldInfo.getAnnotationInfo();
                        for (AnnotationInfo oneFieldAnnotation: fieldsAnnotationInfos) {
                                if (oneFieldAnnotation.getAnnotationType() == clazz) {
                                    fieldIdentifiers.add(new FieldIdentifier(fieldInfo.getClassName(), fieldInfo.getFieldName(), fieldInfo.getType()));
                                }

                        }
                }
            }
        Set<Field> fields = new HashSet<>(fieldIdentifiers.size());
        for (FieldIdentifier fieldIdentifier : fieldIdentifiers) {
            Class<?> classe = scanResult.classNameToClassRef(fieldIdentifier.getClassName());
            for (Field field : classe.getDeclaredFields()) {
                if (fieldIdentifier.matchesField(classe, field)){
                    fields.add(field);
                }
            }
        }
        return fields;
    }

}
