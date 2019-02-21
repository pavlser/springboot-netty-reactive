FROM oracle/graalvm-ce:1.0.0-rc12
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} target/spring-boot-2.1-on-netty-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/spring-boot-2.1-on-netty-0.0.1-SNAPSHOT.jar"]
