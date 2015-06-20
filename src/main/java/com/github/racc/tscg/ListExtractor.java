package com.github.racc.tscg;

import java.lang.reflect.Type;
import java.util.List;

import com.typesafe.config.Config;

/**
 * Extracts {@link List} values from a particular {@link Config}.
 * 
 * @author jason
 */
public interface ListExtractor {
	/**
	 * @param config the {@link Config} to extract from
	 * @param path the {@link Config} path
	 * @return the extracted list value
	 */
	public List<?> extractListValue(Config config, String path);

	/**
	 * @return the {@link List} type this {@link ListExtractor} extracts.
	 */
	public Type getMatchingParameterizedType();
}


