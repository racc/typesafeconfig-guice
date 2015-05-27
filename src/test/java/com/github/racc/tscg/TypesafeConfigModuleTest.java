package com.github.racc.tscg;

import org.junit.Before;
import org.reflections.Reflections;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class TypesafeConfigModuleTest {

	@Before
	public void setup() {
		Config testConf = ConfigFactory.load("conf/test.conf");
		Injector injector = Guice.createInjector(new TypesafeConfigModule(testConf, new Reflections("*")));
	}
	
}
