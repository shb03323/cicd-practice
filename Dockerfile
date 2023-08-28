FROM arm64v8/amazoncorretto:17

WORKDIR /app

COPY ./build/libs/cicd-0.0.1-SNAPSHOT.jar /app/cicd.jar

CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=dev", "cicd.jar"]
