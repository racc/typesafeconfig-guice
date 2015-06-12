package com.github.racc.tscg;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.time.Duration;
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
import com.typesafe.config.ConfigMemorySize;
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
					Type type = p.getAnnotatedType().getType();
					Key<Object> key = (Key<Object>) Key.get(type, annotation);
					String configPath = annotation.value();
					bind(key).toInstance(getConfigValue(p.getType(), configPath));
				}
			}
		}
	}
	
	private Object getConfigValue(Class<?> paramClass, String path) {
		// Handle the special boolean case which allows "Yes" or "No"
		if (paramClass.equals(Boolean.class) || paramClass.equals(boolean.class)) {
			return config.getBoolean(path);
		}
		
		if (!paramClass.isPrimitive()) {
			if (paramClass.equals(Duration.class)) {
				return config.getDuration(path);
			} else if (paramClass.equals(ConfigMemorySize.class)) {
				return config.getMemorySize(path);
			}
			
			ConfigValue configValue = config.getValue(path);
			if (configValue.valueType().equals(ConfigValueType.OBJECT)) {
				Object bean = ConfigBeanFactory.create(config.getConfig(path), paramClass);
				return bean;
			}
		}
		
		return config.getAnyRef(path);
	}
}