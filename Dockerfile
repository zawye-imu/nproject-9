FROM openjdk:latest
COPY ./target/nproject-9.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "nproject-9.jar", "db:3306"]