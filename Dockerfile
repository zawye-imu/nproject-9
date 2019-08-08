FROM openjdk:latest
COPY ./target/project-0.1.0.2-jar-with-dependencies.jar /
WORKDIR /
ENTRYPOINT ["java", "-jar", "project-0.1.0.2-jar-with-dependencies.jar"]