package com.github.racc.tscg;


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
		Assert.assertEquals(1, pojo.getTestInt());
		Assert.assertEquals(2.0, pojo.getTestDouble(), 0.001);
		Assert.assertEquals("test", pojo.getTestString());
		Assert.assertEquals(Arrays.asList("a", "b", "c"), pojo.getTestListOfString());
	}
	
}
