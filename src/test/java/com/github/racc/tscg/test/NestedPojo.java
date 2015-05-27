package com.github.racc.tscg.test;


public class NestedPojo {
	private final int nestInt;
	private final double nestDouble;
	private final String nestString;

	public NestedPojo(
		int testInt,	
		double testDouble,
		String testString
	) {
		this.nestInt = testInt;
		this.nestDouble = testDouble;
		this.nestString = testString;
	}
	
}
