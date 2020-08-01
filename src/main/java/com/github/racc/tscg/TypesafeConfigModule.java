package com.github.racc.tscg;

import com.github.racc.tscg.reflectors.classgraph.ClassGraphReflector;
import com.github.racc.tscg.reflectors.reflections.ReflectionsReflector;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Key;
import com.google.inject.Module;
import com.typesafe.config.*;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.*;
import java.util.Optional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Include this {@link Module} in your {@link Guice} bootstrapping to Automagically
 * get bindings to your {@link TypesafeConfig} annotated configuration parameters.
 *
 * @author jason
 */
public class TypesafeConfigModule extends AbstractModule {

    private final Config config;
    private final Reflector reflections;
    private final Set<TypesafeConfig> boundAnnotations;

    private TypesafeConfigModule(Config config, Reflector reflections) {
        this.config = config;
        this.reflections = reflections;
        this.boundAnnotations = new HashSet<>();
    }

    /**
     * Essentially calls fromConfigWithPackage with a package name of "" to construct a TypesafeConfigModule.
     *
     * @param config the config to use.
     * @return The constructed TypesafeConfigModule.
     * @deprecated this is deprecated as there is a known issue with scanning the classpath for annotations
     * in this mode, when running a packaged jar file.
     * use {@link #fromConfigWithPackage(Config, String)} instead and limit the packages to scan.
     */
    @Deprecated
    public static TypesafeConfigModule fromConfig(Config config) {
        return fromConfigWithPackage(config, "");
    }

    /**
     * Scans the specified packages for annotated classes, and applies Config values to them.
     *
     * @param config            the Config to derive values from
     * @param packageNamePrefix the prefix to limit scanning to - e.g. "com.github"
     * @return The constructed TypesafeConfigModule.
     */
    public static TypesafeConfigModule fromConfigWithPackage(Config config, String packageNamePrefix) {
        Reflections reflections = createPackageScanningReflections(packageNamePrefix);
        return fromConfigWithReflections(config, reflections);
    }

    /**
     * Scans the specified packages for annotated classes, and applies Config values to them.
     *
     * @param config      the Config to derive values from
     * @param reflections the reflections object to use
     * @return The constructed TypesafeConfigModule.
     */
    public static TypesafeConfigModule fromConfigWithReflections(Config config, Reflections reflections) {
        return new TypesafeConfigModule(config, new ReflectionsReflector(reflections));
    }

    /**
     * Scans the specified packages for annotated classes, and applies Config values to them.
     *
     * @param config the Config to derive values from
     * @param scannerSpec package specification for the scanner
     * @return The constructed TypesafeConfigModule.
     */
    public static TypesafeConfigModule fromConfigUsingClasspathScanner(Config config, String... scannerSpec) {
        return new TypesafeConfigModule(config, new ClassGraphReflector(scannerSpec));
    }

    public static Reflections createPackageScanningReflections(String packageNamePrefix) {
        ConfigurationBuilder configBuilder =
                new ConfigurationBuilder()
                        .filterInputsBy(new FilterBuilder().includePackage(packageNamePrefix))
                        .setUrls(ClasspathHelper.forPackage(packageNamePrefix))
                        .setScanners(
                                new TypeAnnotationsScanner(),
                                new MethodParameterScanner(),
                                new MethodAnnotationsScanner(),
                                new FieldAnnotationsScanner()
                        );
        return new Reflections(configBuilder);
    }

    @SuppressWarnings({"rawtypes"})
    @Override
    protected void configure() {
        final Set<Constructor<?>> annotatedConstructors = reflections.getConstructorsWithAnyParamAnnotated(TypesafeConfig.class);
        for (Constructor constructor : annotatedConstructors) {
            Parameter[] params = constructor.getParameters();
            bindParameters(params);
        }

        final Set<Method> annotatedMethods = reflections.getMethodsWithAnyParamAnnotated(TypesafeConfig.class);
        for (Method method : annotatedMethods) {
            Parameter[] params = method.getParameters();
            bindParameters(params);
        }
        final Set<Field> annotatedFields = reflections.getFieldsAnnotatedWith(TypesafeConfig.class);
        for (Field field : annotatedFields) {
            TypesafeConfig annotation = field.getAnnotation(TypesafeConfig.class);
            bindValue(field.getType(), field.getAnnotatedType().getType(), annotation);
        }
    }

    private void bindParameters(Parameter[] params) {
        for (Parameter p : params) {
            if (p.isAnnotationPresent(TypesafeConfig.class)) {
                TypesafeConfig annotation = p.getAnnotation(TypesafeConfig.class);
                bindValue(p.getType(), p.getAnnotatedType().getType(), annotation);
            }
        }
    }

    private void bindValue(Class<?> paramClass, Type paramType, TypesafeConfig annotation) {
        // Prevents multiple bindings on the same annotation
        if (!boundAnnotations.contains(annotation)) {
            @SuppressWarnings("unchecked")
            Key<Object> key = (Key<Object>) Key.get(paramType, annotation);
            String configPath = annotation.value();
            Object configValue = getConfigValue(paramClass, paramType, configPath);
            bind(key).toInstance(configValue);
            boundAnnotations.add(annotation);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Object getConfigValue(Class<?> paramClass, Type paramType, String path) {
        Optional<Object> extractedValue = ConfigExtractors.extractConfigValue(config, paramClass, path);
        if (extractedValue.isPresent()) {
            return extractedValue.get();
        }

        ConfigValue configValue = config.getValue(path);
        ConfigValueType valueType = configValue.valueType();
        if (valueType.equals(ConfigValueType.OBJECT) && Map.class.isAssignableFrom(paramClass)) {
            ConfigObject object = config.getObject(path);
            return object.unwrapped();
        } else if (valueType.equals(ConfigValueType.OBJECT)) {
            return ConfigBeanFactory.create(config.getConfig(path), paramClass);
        } else if (valueType.equals(ConfigValueType.LIST) && List.class.isAssignableFrom(paramClass)) {
            Type listType = ((ParameterizedType) paramType).getActualTypeArguments()[0];

            Optional<List<?>> extractedListValue =
                    ListExtractors.extractConfigListValue(config, listType, path);

            if (extractedListValue.isPresent()) {
                return extractedListValue.get();
            } else {
                List<? extends Config> configList = config.getConfigList(path);
                return configList.stream()
                        .map(cfg -> ConfigBeanFactory.create(cfg, (Class) listType))
                        .collect(Collectors.toList());
            }
        }

        throw new RuntimeException("Cannot obtain config value for " + paramType + " at path: " + path);
    }
}