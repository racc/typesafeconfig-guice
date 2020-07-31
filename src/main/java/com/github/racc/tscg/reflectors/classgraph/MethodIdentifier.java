package com.github.racc.tscg.reflectors.classgraph;

import io.github.classgraph.MethodInfo;

public class MethodIdentifier {
    private final MethodInfo methodInfo;

    public MethodIdentifier(MethodInfo methodInfo) {
        this.methodInfo = methodInfo;
    }

    public MethodInfo getMethodInfo() {
        return methodInfo;
    }

    public String getClassName() {
        return methodInfo.getClassInfo().getName();
    }

    public String getMethodName() {
        return methodInfo.getName();
    }

    public boolean matchesMethod(MethodInfo info) {
        return info.compareTo(methodInfo) == 0;
    }
}
