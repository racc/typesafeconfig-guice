package com.github.racc.tscg.test;

import java.time.Duration;
import java.util.List;

import com.github.racc.tscg.TypesafeConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.typesafe.config.ConfigMemorySize;

@Singleton
public class Pojo {

	private final boolean testBoolean;
	private final boolean testYesBoolean;
	private final int testInt;
	private final double testDouble;
	private final String testString;
	private final List<String> testListOfString;
	private final List<Integer> testListOfInteger;
	private final Duration testDuration;
	private final ConfigMemorySize testSize;
	private final NestedPojo testNestedPojo;
	
	@Inject
	public Pojo(
		@TypesafeConfig("test.boolean") boolean testBoolean,	
		@TypesafeConfig("test.yesBoolean") boolean testYesBoolean,	
		@TypesafeConfig("test.int") int testInt,	
		@TypesafeConfig("test.double") double testDouble,
		@TypesafeConfig("test.string") String testString,
		@TypesafeConfig("test.list.string") List<String> testListOfString,
		@TypesafeConfig("test.list.integer") List<Integer> testListOfInteger,
		@TypesafeConfig("test.duration") Duration testDuration,
		@TypesafeConfig("test.size") ConfigMemorySize testSize,
		@TypesafeConfig("test.nested") NestedPojo testNestedPojo
	) {
		this.testBoolean = testBoolean;
		this.testYesBoolean = testYesBoolean;
		this.testInt = testInt;
		this.testDouble = testDouble;
		this.testString = testString;
		this.testListOfString = testListOfString;
		this.testListOfInteger = testListOfInteger;
		this.testDuration = testDuration;
		this.testSize = testSize;
		this.testNestedPojo = testNestedPojo;
	}
	
	public boolean isTestBoolean() {
		return testBoolean;
	}
	
	public boolean isTestYesBoolean() {
		return testYesBoolean;
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
	
	public List<Integer> getTestListOfInteger() {
		return testListOfInteger;
	}
	
	public Duration getTestDuration() {
		return testDuration;
	}
	
	public ConfigMemorySize getTestSize() {
		return testSize;
	}
	
	public NestedPojo getTestNestedPojo() {
		return testNestedPojo;
	}
}
