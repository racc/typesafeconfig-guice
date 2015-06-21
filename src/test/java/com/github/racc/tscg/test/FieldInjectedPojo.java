package com.github.racc.tscg.test;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import com.github.racc.tscg.TypesafeConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.typesafe.config.ConfigMemorySize;

@Singleton
public class FieldInjectedPojo implements TestPojo {

	@Inject @TypesafeConfig("test.boolean") 	  boolean testBoolean;
	@Inject @TypesafeConfig("test.yesBoolean") 	  boolean testYesBoolean;
	@Inject @TypesafeConfig("test.long") 		  long testLong;
	@Inject @TypesafeConfig("test.byte") 		  byte testByte;
	@Inject @TypesafeConfig("test.int") 		  int testInt;
	@Inject @TypesafeConfig("test.double") 		  double testDouble;
	@Inject @TypesafeConfig("test.float") 		  float testFloat;
	@Inject @TypesafeConfig("test.string") 		  String testString;
	@Inject @TypesafeConfig("test.list.boolean")  List<Boolean> testListOfBoolean;
	@Inject @TypesafeConfig("test.list.integer")  List<Integer> testListOfInteger;
	@Inject @TypesafeConfig("test.list.double")   List<Double> testListOfDouble;
	@Inject @TypesafeConfig("test.list.long") 	  List<Long> testListOfLong;
	@Inject @TypesafeConfig("test.list.string")   List<String> testListOfString;
	@Inject @TypesafeConfig("test.list.duration") List<Duration> testListOfDuration;
	@Inject @TypesafeConfig("test.list.size") 	  List<ConfigMemorySize> testListOfSize;
	@Inject @TypesafeConfig("test.list.nested")   List<NestedPojo> testListOfNested;
	@Inject @TypesafeConfig("test.duration") 	  Duration testDuration;
	@Inject @TypesafeConfig("test.size") 		  ConfigMemorySize testSize;
	@Inject @TypesafeConfig("test.map") 		  Map<String, Integer> testMap;
	@Inject @TypesafeConfig("test.map.intkey") 	  Map<Integer, String> testMapIntkey;
	@Inject @TypesafeConfig("test.nested") 		  NestedPojo testNestedPojo;
	
	public void setTestBoolean(boolean testBoolean) {
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
