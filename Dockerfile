FROM amazoncorretto:17

COPY build/libs/cicd-0.0.1-SNAPSHOT.jar cicd.jar

CMD ["java", "-jar", "-Dspring.profiles.active=dev", "cicd.jar"]
