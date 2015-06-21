# typesafeconfig-guice
Allows [Guice](https://github.com/google/guice) to @Inject configuration values into your application derived from [Typesafe Config](https://github.com/typesafehub/config).

[![Build Status](https://travis-ci.org/racc/typesafeconfig-guice.svg?branch=master)](https://travis-ci.org/racc/typesafeconfig-guice)

Available on Maven: [https://oss.sonatype.org/content/repositories/releases/](https://oss.sonatype.org/content/repositories/releases/com/github/racc/typesafeconfig-guice/).
 
```xml
<dependency>
    <groupId>com.github.racc</groupId>
    <artifactId>typesafeconfig-guice</artifactId>
    <version>0.0.1</version>
</dependency>
```

### Quickstart
#### Step 1:
Annotate configuration injection points (Constructors, Providers, Provider methods, Setter methods, Fields) with ```@TypesafeConfig("config.path.key")```

#### Step 2:
Bootstrap your application with Guice and the TypesafeConfigModule.
Configuration values annotated with @TypesafeConfig will be scanned for on the classpath, and then bound from your supplied Typesafe Config file.
```java
Config config = ConfigFactory.load("config.conf");
Injector injector = Guice.createInjector(
	TypesafeConfigModule.fromConfig(testConf)
	// ... Add your other modules here
);
```

#### Step 3:
Profit!

### Features
- Plain Old Java Objects which follow JavaBean conventions (zero-args constructor, getters and setters) can be injected. 
- Supports injections of `java.time.Duration` objects with config parameters defined as "10 seconds", "10 minutes" etc.
- Supports injections of `com.typesafe.config.ConfigMemorySize` objects with config parameters defined as "512k" or "2M" etc.
- Supports injections of `boolean`, where the matching config parameter can be "true", "false", "yes" or "no".
- Supports injections of List types of primitives: `boolean`, `int`, `double`, `long`, `string`, `java.time.Duration`, `com.typesafe.config.ConfigMemorySize`
- Supports injections of `java.util.Map<String, Object>`
- Supports injections of POJO Lists.
- Extensive test coverage

AUTHOR
-----------
Jason Then (mailto:then.jason@gmail.com)

Any comments and feedback appreciated!

LICENSE
-----------
Copyright 2015 Jason Then

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.