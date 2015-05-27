package com.github.racc.tscg;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.typesafe.config.Config;

public class TypesafeConfigModule extends AbstractModule {

	private final Config config;
	private final Reflections reflections;

	private TypesafeConfigModule(Config config, Reflections reflections) {
		this.config = config;
		this.reflections = reflections;
	}
	
	public static TypesafeConfigModule fromConfig(Config config) {
		return fromConfigWithPackage(config, "");
	}
	
	public static TypesafeConfigModule fromConfigWithPackage(Config config, String packageName) {
		 Reflections reflections = new Reflections(new ConfigurationBuilder()
         .filterInputsBy(new FilterBuilder().includePackage(packageName))
         .setUrls(ClasspathHelper.forPackage(packageName))
         .setScanners(new TypeAnnotationsScanner(), new MethodParameterScanner()));
		
		return new TypesafeConfigModule(config, reflections);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void configure() {
		Set<Constructor> annotatedConstructors = reflections.getConstructorsWithAnyParamAnnotated(TypesafeConfig.class);
		for (Constructor c : annotatedConstructors) {
			Parameter[] params = c.getParameters();
			for (Parameter p : params) {
				if (p.isAnnotationPresent(TypesafeConfig.class)) {
					TypesafeConfig annotation = p.getAnnotation(TypesafeConfig.class);
					Type type = p.getAnnotatedType().getType();
					Key<Object> key = (Key<Object>) Key.get(type, annotation);
					String configPath = annotation.value();
					bind(key).toInstance(getConfigValue(type, configPath));
				}
			}
		}
	}
	
	private Object getConfigValue(Type type, String path) {
		return config.getAnyRef(path);
	}
}