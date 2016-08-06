# JvmextLookup for Log4J 2.x

Custom "JVM" Lookup (jvmext) for Log4j2. Open Source Java project under Apache License v2.0

### Current Stable Version is [1.0.0](https://search.maven.org/#search|ga|1|g%3Aorg.javastack%20a%3Alog4j2-jvmext)

---

## DOC

### Jvmext Lookup

The JvmextLookup allows JVM environment information to be retrieved in convenient preformatted strings using the `jvmext:` prefix.

| Key       | Description |
| :-------- | :---------- |
| node      | Hostname of the machine we are running on or "UNKNOWN_LOCALHOST" in case where the host name cannot be resolved. |
| pid       | The PID (process id) of the running Java virtual machine or 0 if unknown. |
| startTime | Returns the approximate time in milliseconds when the Java virtual machine started. |

#### Usage Examples

```properties
# log4j2.properties 
status = warn
name = PropertiesConfig

# Console
appender.console.type = Console
appender.console.name = console
appender.console.layout.type = PatternLayout
appender.console.layout.pattern = %d{ISO8601} [${jvmext:node}:${jvmext:pid}] [%t] %p %c (DC:%MDC:%NDC) %m%n

rootLogger.level = info
rootLogger.appenderRef.console.ref = console
```

---

## MAVEN

Add the dependency to your pom.xml:

    <dependency>
        <groupId>org.javastack</groupId>
        <artifactId>log4j2-jvmext</artifactId>
        <version>1.0.0</version>
    </dependency>

---
Inspired in [log4j2-lookups](https://logging.apache.org/log4j/2.x/manual/lookups.html), this code is Java-minimalistic version.
