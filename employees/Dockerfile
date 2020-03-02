#FROM openjdk:13-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:13-jdk-alpine as builder
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
#RUN java -Djarmode=layertools -jar application.jar extract
RUN jar xf application.jar
#
FROM openjdk:13-jdk-alpine
WORKDIR application
COPY --from=builder application/BOOT-INF/ ./
COPY --from=builder application/META-INF/ ./
COPY --from=builder application/org/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
