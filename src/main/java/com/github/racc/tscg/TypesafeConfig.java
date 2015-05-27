package com.github.racc.tscg;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;
import com.typesafe.config.Config;

/**
 * A {@link BindingAnnotation} Used to annotate fields, construc
 * 
 * @author jason
 */
@BindingAnnotation 
@Target({ FIELD, PARAMETER, METHOD }) 
@Retention(RUNTIME)
public @interface TypesafeConfig {
	
	/**
	 * @return the {@link Config} path to the configuration value.
	 */
	public String value();
	
}
