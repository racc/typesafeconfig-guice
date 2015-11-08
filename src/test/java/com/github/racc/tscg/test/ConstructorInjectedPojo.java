package com.github.racc.tscg.test;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import com.github.racc.tscg.TypesafeConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.typesafe.config.ConfigMemorySize;

@Singleton
public class ConstructorInjectedPojo implements TestPojo {

	private final boolean testBoolean;
	private final boolean testYesBoolean;
	private final long testLong;
	private final byte testByte;
	private final int testInt;
	private final double testDouble;
	private final float testFloat;
	private final String testString;
	private final List<Boolean> testListOfBoolean;
	private final List<Integer> testListOfInteger;
	private final List<Double> testListOfDouble;
	private final List<Long> testListOfLong;
	private final List<String> testListOfString;
	private final List<Duration> testListOfDuration;
	private final List<ConfigMemorySize> testListOfSize;
	private final List<NestedPojo> testListOfNested;
	private final Duration testDuration;
	private final ConfigMemorySize testSize;
	private final Map<String, Integer> testMap;
	private final Map<Integer, String> testMapIntkey;
	private final NestedPojo testNestedPojo;
	
	@Inject
	public ConstructorInjectedPojo(
		@TypesafeConfig("constructor.boolean") boolean testBoolean,	
		@TypesafeConfig("constructor.boolean") boolean testBooleanAgain,	
		@TypesafeConfig("constructor.yesBoolean") boolean testYesBoolean,	
		@TypesafeConfig("constructor.long") long testLong,	
		@TypesafeConfig("constructor.byte") byte testByte,	
		@TypesafeConfig("constructor.int") int testInt,	
		@TypesafeConfig("constructor.double") double testDouble,
		@TypesafeConfig("constructor.float") float testFloat,
		@TypesafeConfig("constructor.string") String testString,
		@TypesafeConfig("constructor.list.boolean") List<Boolean> testListOfBoolean,
		@TypesafeConfig("constructor.list.integer") List<Integer> testListOfInteger,
		@TypesafeConfig("constructor.list.double") List<Double> testListOfDouble,
		@TypesafeConfig("constructor.list.long") List<Long> testListOfLong,
		@TypesafeConfig("constructor.list.string") List<String> testListOfString,
		@TypesafeConfig("constructor.list.duration") List<Duration> testListOfDuration,
		@TypesafeConfig("constructor.list.size") List<ConfigMemorySize> testListOfSize,
		@TypesafeConfig("constructor.list.nested") List<NestedPojo> testListOfNested,
		@TypesafeConfig("constructor.duration") Duration testDuration,
		@TypesafeConfig("constructor.size") ConfigMemorySize testSize,
		@TypesafeConfig("constructor.map") Map<String, Integer> testMap,
		@TypesafeConfig("constructor.map.intkey") Map<Integer, String> testMapIntkey,
		@TypesafeConfig("constructor.nested") NestedPojo testNestedPojo
	) {
		this.testBoolean = testBoolean;
		this.testYesBoolean = testYesBoolean;
		this.testLong = testLong;
		this.testByte = testByte;
		this.testInt = testInt;
		this.testDouble = testDouble;
		this.testFloat = testFloat;
		this.testString = testString;
		this.testListOfBoolean = testListOfBoolean;
		this.testListOfInteger = testListOfInteger;
		this.testListOfDouble = testListOfDouble;
		this.testListOfLong = testListOfLong;
		this.testListOfString = testListOfString;
		this.testListOfDuration = testListOfDuration;
		this.testListOfSize = testListOfSize;
		this.testListOfNested = testListOfNested;
		this.testDuration = testDuration;
		this.testSize = testSize;
		this.testMap = testMap;
		this.testMapIntkey = testMapIntkey;
		this.testNestedPojo = testNestedPojo;
	}
	
	public boolean isTestBoolean() {
		return testBoolean;
	}
	
	public boolean isTestYesBoolean() {
		return testYesBoolean;
	}
	
	public long getTestLong() {
		return testLong;
	}
	
	public byte getTestByte() {
		return testByte;
	}

	public int getTestInt() {
		return testInt;
	}
	
	public double getTestDouble() {
		return testDouble;
	}
	
	public float getTestFloat() {
		return testFloat;
	}
	
	public String getTestString() {
		return testString;
	}
	
	public List<Boolean> getTestListOfBoolean() {
		return testListOfBoolean;
	}
	
	public List<Integer> getTestListOfInteger() {
		return testListOfInteger;
	}
	
	public List<Double> getTestListOfDouble() {
		return testListOfDouble;
	}
	
	public List<Long> getTestListOfLong() {
		return testListOfLong;
	}
	
	public List<String> getTestListOfString() {
		return testListOfString;
	}
	
	public List<Duration> getTestListOfDuration() {
		return testListOfDuration;
	}
	
	public List<ConfigMemorySize> getTestListOfSize() {
		return testListOfSize;
	}
	
	public List<NestedPojo> getTestListOfNested() {
		return testListOfNested;
	}
	
	public Duration getTestDuration() {
		return testDuration;
	}
	
	public ConfigMemorySize getTestSize() {
		return testSize;
	}
	
	public Map<String, Integer> getTestMap() {
		return testMap;
	}

	public Map<Integer, String> getTestMapIntkey() {
		return testMapIntkey;
	}
	
	public NestedPojo getTestNestedPojo() {
		return testNestedPojo;
	}
}
