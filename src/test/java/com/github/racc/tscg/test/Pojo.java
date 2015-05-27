package com.github.racc.tscg.test;

import java.util.List;

import com.github.racc.tscg.TypesafeConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Pojo {

	private final int testInt;
	private final double testDouble;
	private final String testString;
	private final List<String> testListOfString;
	private final NestedPojo testNestedPojo;
	
	@Inject
	public Pojo(
		@TypesafeConfig("test.int") int testInt,	
		@TypesafeConfig("test.double") double testDouble,
		@TypesafeConfig("test.string") String testString,
		@TypesafeConfig("test.list.string") List<String> testListOfString,
		@TypesafeConfig("test.nested") NestedPojo testNestedPojo
	) {
		this.testInt = testInt;
		this.testDouble = testDouble;
		this.testString = testString;
		this.testListOfString = testListOfString;
		this.testNestedPojo = testNestedPojo;
	}
	
	public int getTestInt() {
		return testInt;
	}
	
	public double getTestDouble() {
		return testDouble;
	}
	
	public String getTestString() {
		return testString;
	}
	
	public List<String> getTestListOfString() {
		return testListOfString;
	}
	
	public NestedPojo getTestNestedPojo() {
		return testNestedPojo;
	}
}
