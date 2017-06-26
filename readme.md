# Instructions
- To run this project, install [Docker](https://www.docker.com)
- Run: `docker pull animatedlew/logger`
- Verify logs: `docker run -it --rm animatedlew/logger cat /var/log/cota/helix/debug.log`

# Notes
This project uses `sbt assembly` to build a fat jar that moves logback.xml into a central location. It then runs the fat jar with the new location of the logback file. e.g.
```bash
java -Dlogback.configurationFile=/opt/logback.xml -jar target/scala-2.12/log.jar
```
The classpath of files inside of `src/main/resources` does not automatically resolve.

# Build & Run Locally
 - build: `docker build -t animatedlew/logger .`
 - run: `docker run -it --rm animatedlew/logger cat /var/log/cota/helix/debug.log`

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
