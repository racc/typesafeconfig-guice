package com.github.racc.tscg;

import java.lang.reflect.Type;

import com.typesafe.config.Config;

/**
 * Extracts values from a particular {@link Config}.
 * 
 * @author jason
 */
interface ConfigExtractor {
	
	/**
	 * @param config the {@link Config} to extract from
	 * @param paramType the {@link Type} of the matching parameter
	 * @param path the {@link Config} path
	 * @return the extracted value
	 */
	public Object extractValue(Config config, Type paramType, String path);
	
	/**
	 * @return the types this {@link ConfigExtractor} will extract for.
	 */
	public Class<?>[] getMatchingClasses();
}
