FROM openjdk:21-jdk

WORKDIR /app

COPY /target/scholarsync-0.0.1-SNAPSHOT.jar /app/scholarsync.jar

EXPOSE 8080

CMD ["java", "-jar", "scholarsync.jar"]