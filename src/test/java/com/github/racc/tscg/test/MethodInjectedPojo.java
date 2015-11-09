package com.github.racc.tscg.test;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import com.github.racc.tscg.TypesafeConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.typesafe.config.ConfigMemorySize;

@Singleton
public class MethodInjectedPojo implements TestPojo {

	private boolean testBoolean;
	private boolean testYesBoolean;
	private long testLong;
	private byte testByte;
	private int testInt;
	private double testDouble;
	private float testFloat;
	private String testString;
	private List<Boolean> testListOfBoolean;
	private List<Integer> testListOfInteger;
	private List<Double> testListOfDouble;
	private List<Long> testListOfLong;
	private List<String> testListOfString;
	private List<Duration> testListOfDuration;
	private List<ConfigMemorySize> testListOfSize;
	private List<NestedPojo> testListOfNested;
	private Duration testDuration;
	private ConfigMemorySize testSize;
	private Map<String, Integer> testMap;
	private Map<Integer, String> testMapIntkey;
	private NestedPojo testNestedPojo;
	
	@Inject
	public void setTestYesBoolean(@TypesafeConfig("method.boolean") boolean testYesBoolean) {
		this.testYesBoolean = testYesBoolean;
	}

	@Inject
	public void setTestLong(@TypesafeConfig("method.long") long testLong) {
		this.testLong = testLong;
	}

	@Inject
	public void setTestByte(@TypesafeConfig("method.byte") byte testByte) {
		this.testByte = testByte;
	}

	@Inject
	public void setTestInt(@TypesafeConfig("method.int") int testInt) {
		this.testInt = testInt;
	}

	@Inject
	public void setTestDouble(@TypesafeConfig("method.double") double testDouble) {
		this.testDouble = testDouble;
	}

	@Inject
	public void setTestFloat(@TypesafeConfig("method.float") float testFloat) {
		this.testFloat = testFloat;
	}

	@Inject
	public void setTestString(@TypesafeConfig("method.string") 	 String testString) {
		this.testString = testString;
	}
	
	@Inject
	public void setTestListOfBoolean(@TypesafeConfig("method.list.boolean") List<Boolean> testListOfBoolean) {
		this.testListOfBoolean = testListOfBoolean;
	}

	@Inject
	public void setTestListOfInteger(@TypesafeConfig("method.list.integer") List<Integer> testListOfInteger) {
		this.testListOfInteger = testListOfInteger;
	}

	@Inject
	public void setTestListOfDouble(@TypesafeConfig("method.list.double") List<Double> testListOfDouble) {
		this.testListOfDouble = testListOfDouble;
	}

	@Inject
	public void setTestListOfLong(@TypesafeConfig("method.list.long") List<Long> testListOfLong) {
		this.testListOfLong = testListOfLong;
	}

	@Inject
	public void setTestListOfString(@TypesafeConfig("method.list.string") List<String> testListOfString) {
		this.testListOfString = testListOfString;
	}

	@Inject
	public void setTestListOfDuration(@TypesafeConfig("method.list.duration") List<Duration> testListOfDuration) {
		this.testListOfDuration = testListOfDuration;
	}

	@Inject
	public void setTestListOfSize(@TypesafeConfig("method.list.size") List<ConfigMemorySize> testListOfSize) {
		this.testListOfSize = testListOfSize;
	}

	@Inject
	public void setTestListOfNested(@TypesafeConfig("method.list.nested") List<NestedPojo> testListOfNested) {
		this.testListOfNested = testListOfNested;
	}

	@Inject
	public void setTestDuration(@TypesafeConfig("method.duration") Duration testDuration) {
		this.testDuration = testDuration;
	}

	@Inject
	public void setTestSize(@TypesafeConfig("method.size") ConfigMemorySize testSize) {
		this.testSize = testSize;
	}

	@Inject
	public void setTestMap(@TypesafeConfig("method.map") Map<String, Integer> testMap) {
		this.testMap = testMap;
	}

	@Inject
	public void setTestMapIntkey(@TypesafeConfig("method.map.intkey") Map<Integer, String> testMapIntkey) {
		this.testMapIntkey = testMapIntkey;
	}

	@Inject
	public void setTestNestedPojo(@TypesafeConfig("method.nested") NestedPojo testNestedPojo) {
		this.testNestedPojo = testNestedPojo;
	}
	
	@Inject
	public void setTestBoolean(@TypesafeConfig("method.yesBoolean") boolean testBoolean) {
		this.testBoolean = testBoolean;
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
