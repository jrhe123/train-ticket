# train-ticket

train ticket platform

#### Springboot 3

- AOT vs. JIT
- JIT: just in time (regular spring boot project, e.g., springboot 2)
- AOT: ahead of time
  - no JDK needed in your server
  - very fast to start
  - not support AOP (e.g., log interceptor)
  - pre-compile, work with machine code, .exe instead of jar

#### JIT issue

- warm up
  - no traffic
  - use 1 thread to warm up the feat (required JIT compile -> machine code execute)
  - let traffic in

#### GraalVM to support AOT

- use GraalVM instead of JVM
- download: https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-22.3.0
- config env variables
  - JAVA_HOME
  - Path
- cmd: gu install native-image
- cmd: gu list
- visual studio 2022: Native Tools Command Prompt for VS 2022
- cmd: mvn -Pnative native:compile

#### Init new project

- Dependencies:
  - GraalVM Native Support
  - Spring Web
- Use "Native Tools Command Prompt for VS 2022", to compile the app
- cmd: mvn -Pnative native:compile
- After you build, we're getting xxx.exe for your project
- pom.xml

```
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>3.0.0</version> <!-- make sure it's 3.0.0 -->
  <relativePath />
</parent>

<build>
  <plugins>
    <plugin>
      <groupId>org.graalvm.buildtools</groupId>
      <artifactId>native-maven-plugin</artifactId>
    </plugin>

    ...

  </plugins>
</build>
```

#### Tools

- application.properties <-> application.yaml
- https://toyaml.com/index.html
