FROM openjdk:17-oracle
ARG JAR_FILE=build/libs/takehome-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]