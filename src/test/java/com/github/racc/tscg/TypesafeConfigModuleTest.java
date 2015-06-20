package com.github.racc.tscg;


import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.racc.tscg.test.Pojo;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigMemorySize;

public class TypesafeConfigModuleTest {

	private Injector injector;

	@Before
	public void setup() {
		Config testConf = ConfigFactory.load("conf/test.conf");
		Module testModule = new AbstractModule() {
			@Override
			protected void configure() {
				bind(Pojo.class).asEagerSingleton();
			}
		};
		
		injector = Guice.createInjector(
			TypesafeConfigModule.fromConfig(testConf),
			testModule
		);
	}
	
	@Test
	public void canInjectPojo() {
		Pojo pojo = injector.getInstance(Pojo.class);
		Assert.assertTrue(pojo.isTestBoolean());
		Assert.assertTrue(pojo.isTestYesBoolean());
		Assert.assertEquals(12345679123l, pojo.getTestLong());
		Assert.assertEquals(1, pojo.getTestInt());
		Assert.assertEquals(123, pojo.getTestByte());
		Assert.assertEquals(2.0, pojo.getTestDouble(), 0.001);
		Assert.assertEquals(2.0f, pojo.getTestFloat(), 0.001);
		Assert.assertEquals("test", pojo.getTestString());
		Assert.assertEquals(Duration.of(10, ChronoUnit.SECONDS), pojo.getTestDuration());
		Assert.assertEquals(ConfigMemorySize.ofBytes(524288), pojo.getTestSize());
		Assert.assertEquals(Arrays.asList(true, false, true), pojo.getTestListOfBoolean());
		Assert.assertEquals(Arrays.asList(1, 2, 3), pojo.getTestListOfInteger());
		Assert.assertEquals(Arrays.asList(1.1, 2.2, 3.3), pojo.getTestListOfDouble());
		Assert.assertEquals(Arrays.asList(12345679121L, 12345679122L, 12345679123L), pojo.getTestListOfLong());
		Assert.assertEquals(Arrays.asList("a", "b", "c"), pojo.getTestListOfString());
		Assert.assertEquals(Arrays.asList(Duration.of(1, ChronoUnit.SECONDS), Duration.of(2, ChronoUnit.SECONDS), Duration.of(3, ChronoUnit.SECONDS)), pojo.getTestListOfDuration());
		Assert.assertEquals(Arrays.asList(ConfigMemorySize.ofBytes(524288), ConfigMemorySize.ofBytes(1048576), ConfigMemorySize.ofBytes(1073741824)), pojo.getTestListOfSize());
	}
}
