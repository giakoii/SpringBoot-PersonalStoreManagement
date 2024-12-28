FROM openjdk:22

ARG FILE_JAR=target/personal-store-management-project-0.0.1-SNAPSHOT.jar

ADD ${FILE_JAR} api-service-store-management.jar

ENTRYPOINT ["java", "-jar", "api-service-store-management.jar"]

EXPOSE 2003