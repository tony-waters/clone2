FROM openjdk:8-alpine

COPY target/clone1-*.jar clone1.jar

ENTRYPOINT ["java", "-jar", "clone1.jar", "--server.port=80"]