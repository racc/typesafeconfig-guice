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

	@Inject @TypesafeConfig("field.boolean") 	  boolean testBoolean;
	@Inject @TypesafeConfig("field.yesBoolean") 	  boolean testYesBoolean;
	@Inject @TypesafeConfig("field.long") 		  long testLong;
	@Inject @TypesafeConfig("field.byte") 		  byte testByte;
	@Inject @TypesafeConfig("field.int") 		  int testInt;
	@Inject @TypesafeConfig("field.double") 		  double testDouble;
	@Inject @TypesafeConfig("field.float") 		  float testFloat;
	@Inject @TypesafeConfig("field.string") 		  String testString;
	@Inject @TypesafeConfig("field.list.boolean")  List<Boolean> testListOfBoolean;
	@Inject @TypesafeConfig("field.list.integer")  List<Integer> testListOfInteger;
	@Inject @TypesafeConfig("field.list.double")   List<Double> testListOfDouble;
	@Inject @TypesafeConfig("field.list.long") 	  List<Long> testListOfLong;
	@Inject @TypesafeConfig("field.list.string")   List<String> testListOfString;
	@Inject @TypesafeConfig("field.list.duration") List<Duration> testListOfDuration;
	@Inject @TypesafeConfig("field.list.size") 	  List<ConfigMemorySize> testListOfSize;
	@Inject @TypesafeConfig("field.list.nested")   List<NestedPojo> testListOfNested;
	@Inject @TypesafeConfig("field.duration") 	  Duration testDuration;
	@Inject @TypesafeConfig("field.size") 		  ConfigMemorySize testSize;
	@Inject @TypesafeConfig("field.map") 		  Map<String, Integer> testMap;
	@Inject @TypesafeConfig("field.map.intkey") 	  Map<Integer, String> testMapIntkey;
	@Inject @TypesafeConfig("field.nested") 		  NestedPojo testNestedPojo;
	
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
