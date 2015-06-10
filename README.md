# typesafeconfig-guice
Allows Guice to @Inject configuration values into your application derived from Typesafe Config.

[![Build Status](https://travis-ci.org/racc/typesafeconfig-guice.svg?branch=master)](https://travis-ci.org/racc/typesafeconfig-guice)

```xml
<dependency>
    <groupId>com.github.racc</groupId>
    <artifactId>typesafeconfig-guice</artifactId>
    <version>0.0.1</version>
</dependency>
```

### Quickstart
#### Step 1:
Annotate configuration injection points (Constructors, Providers, Fields) with ```@TypesafeConfig("config.path.key")```

#### Step 2:
Bootstrap your application with Guice and the TypesafeConfigModule.
Configuration values annotated with @TypesafeConfig will be
derived from your Typesafe Config file.
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


AUTHOR
-----------
Jason Then (mailto:then.jason@gmail.com)


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