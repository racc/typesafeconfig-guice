package com.github.racc.tscg;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Key;
import com.google.inject.Module;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;

/**
 * Include this {@link Module} in your {@link Guice} bootstrapping to Automagically
 * get bindings to your {@link TypesafeConfig} annotated configuration parameters.
 * 
 * @author jason
 */
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
					Type paramType = p.getAnnotatedType().getType();
					Key<Object> key = (Key<Object>) Key.get(paramType, annotation);
					String configPath = annotation.value();
					Class<?> paramClass = p.getType();
					Object configValue = getConfigValue(paramClass, paramType, configPath);
					bind(key).toInstance(configValue);
				}
			}
		}
	}
	
	private Object getConfigValue(Class<?> paramClass, Type paramType, String path) {
		Optional<Object> extractedValue = ConfigExtractors.extractConfigValue(config, paramClass, path);
		if (extractedValue.isPresent()) {
			return extractedValue.get();
		}

		ConfigValue configValue = config.getValue(path);
		ConfigValueType valueType = configValue.valueType();
		if (valueType.equals(ConfigValueType.OBJECT)) {
			Object bean = ConfigBeanFactory.create(config.getConfig(path), paramClass);
			return bean;
		} else if (valueType.equals(ConfigValueType.LIST) && List.class.isAssignableFrom(paramClass)) {
			Type listType = ((ParameterizedType) paramType).getActualTypeArguments()[0];
			
			Optional<List<?>> extractedListValue = 
					ListExtractors.extractConfigListValue(config, listType, path);
			
			if (extractedListValue.isPresent()) {
				return extractedListValue.get();
			}
		}
		
		throw new RuntimeException("Cannot obtain config value for " + paramType + " at path: " + path);
	}
}