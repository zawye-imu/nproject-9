FROM openjdk:latest
COPY ./target/project-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "project-0.1.0.2-jar-with-dependencies.jar"]