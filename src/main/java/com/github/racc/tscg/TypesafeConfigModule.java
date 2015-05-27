package com.github.racc.tscg;

import java.lang.reflect.Constructor;
import java.util.Set;

import org.reflections.Reflections;

import com.google.inject.AbstractModule;
import com.typesafe.config.Config;

public class TypesafeConfigModule extends AbstractModule {

	private final Config config;
	private final Reflections reflections;

	public TypesafeConfigModule(Config config, Reflections reflections) {
		this.config = config;
		this.reflections = reflections;
	}
	
	public static TypesafeConfigModule fromClasspath(Config config, String packageName) {
		return new TypesafeConfigModule(config, new Reflections(packageName));
	}
	
	@Override
	protected void configure() {
		Set<Constructor> annotatedConstructors = reflections.getConstructorsAnnotatedWith(TypesafeConfig.class);
		
	}
}