FROM amazoncorretto:17.0.7-alpine

WORKDIR /app

COPY build/libs/restful-movies.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]