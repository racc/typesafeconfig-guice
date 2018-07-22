package com.github.racc.tscg.reflectors.fastclasspathscanner;

import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import io.github.lukehutch.fastclasspathscanner.typesignature.TypeSignature;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MethodIdentifier {
    private final String className;
    private final String methodName;
    private final TypeSignature[] parameterTypeSignature;

    public MethodIdentifier(String className, String methodName, TypeSignature[] parameterTypeSignature) {
        this.className = className;
        this.methodName = methodName;
        this.parameterTypeSignature = parameterTypeSignature;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public TypeSignature[] getParameterTypeSignature() {
        return parameterTypeSignature;
    }

    public boolean matchesMethod(Class<?> clazz, Method method, ScanResult scanResult) {
        if (clazz.getName().equals(className)
                && method.getName().equals(methodName)
                && method.getParameterCount() == parameterTypeSignature.length)
        {
            boolean paramsMatch = true;
            for (int i = 0; i < parameterTypeSignature.length; i++) {
                paramsMatch = paramsMatch && parameterTypeSignature[i].instantiate(scanResult) == method.getParameters()[i].getType();
            }
            return paramsMatch;
        }
        return false;
    }

    public boolean matchesConstructor(Class<?> clazz, Constructor<?> constructor, ScanResult scanResult) {
        if (clazz.getCanonicalName().equals(className) && methodName.equals("<init>") && constructor.getParameterCount() == parameterTypeSignature.length)
        {
            boolean paramsMatch = true;
            for (int i = 0; i < parameterTypeSignature.length; i++) {
                paramsMatch = paramsMatch && parameterTypeSignature[i].instantiate(scanResult) == constructor.getParameters()[i].getType();
            }
            return paramsMatch;
        }
        return false;
    }
}
