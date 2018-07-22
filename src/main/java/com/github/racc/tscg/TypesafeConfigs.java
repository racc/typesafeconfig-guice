package com.github.racc.tscg;

public class TypesafeConfigs {

    public static TypesafeConfig forKeypath(String keypath) {
        return new TypesafeConfigImpl(keypath);
    }
}
