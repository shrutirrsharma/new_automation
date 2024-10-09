FROM openjdk:17
WORKDIR /automation
COPY . .
EXPOSE 9082
ADD target/automation-0.0.1-SNAPSHOT.jar /automation-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/automation-0.0.1-SNAPSHOT.jar" ]
