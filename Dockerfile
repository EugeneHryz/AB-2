FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar *.jar
ENTRYPOINT ["java", "-jar", "/*.jar"]