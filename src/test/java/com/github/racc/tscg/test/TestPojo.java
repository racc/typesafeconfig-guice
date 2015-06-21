package com.github.racc.tscg.test;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import com.typesafe.config.ConfigMemorySize;

public interface TestPojo {

	public boolean isTestBoolean();
	
	public boolean isTestYesBoolean();
	
	public long getTestLong();
	
	public byte getTestByte();

	public int getTestInt();

	public double getTestDouble();
	
	public float getTestFloat();
	
	public String getTestString();

	public List<Boolean> getTestListOfBoolean();
	
	public List<Integer> getTestListOfInteger();
	
	public List<Double> getTestListOfDouble();
	
	public List<Long> getTestListOfLong();
	
	public List<String> getTestListOfString();
	
	public List<Duration> getTestListOfDuration();
	
	public List<ConfigMemorySize> getTestListOfSize();
	
	public List<NestedPojo> getTestListOfNested();
	
	public Duration getTestDuration();
	
	public ConfigMemorySize getTestSize();
	
	public Map<String, Integer> getTestMap();

	public Map<Integer, String> getTestMapIntkey();
	
	public NestedPojo getTestNestedPojo();
}
