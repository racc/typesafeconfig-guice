package com.github.racc.tscg.test;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Pojo {

	private final int testInt;
	private final double testDouble;
	private final String testString;
	
	@Inject
	public Pojo(
		int testInt,	
		double testDouble,
		String testString
	) {
		this.testInt = testInt;
		this.testDouble = testDouble;
		this.testString = testString;
	}
	
}
