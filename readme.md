# Instructions
- `sbt assembly` to build fat jar with no logback.xml (we will override it if it exists)
- `java -Dlogback.configurationFile=/opt/logback.xml -jar target/scala-2.12/log.jar`
- Check out `/var/log/helix/debug.log`

# Docker
 - build: `docker build -t cota/helix .`
 - run: `docker run -it --rm cota/helix cat /var/log/cota/helix/debug.log`

# Example logback.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

	<property name="DEV_HOME" value="/var/log/cota/helix" />

	<appender name="FILE-AUDIT"
		class="ch.qos.logback.core.rolling.RollingFileAppender">
		<file>${DEV_HOME}/debug.log</file>
		<encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
			<Pattern>
				%d{yyyy-MM-dd HH:mm:ss} [%-5level] - %msg%n
			</Pattern>
		</encoder>

		<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<!-- rollover daily -->
			<fileNamePattern>${DEV_HOME}/archived/debug.%d{yyyy-MM-dd}.%i.log
                        </fileNamePattern>
			<timeBasedFileNamingAndTriggeringPolicy
				class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
				<maxFileSize>10MB</maxFileSize>
			</timeBasedFileNamingAndTriggeringPolicy>
		</rollingPolicy>

	</appender>

	<logger name="com.animatedlew" level="debug" additivity="false">
		<appender-ref ref="FILE-AUDIT" />
	</logger>

	<root level="error">
		<appender-ref ref="FILE-AUDIT" />
	</root>

</configuration>
```
