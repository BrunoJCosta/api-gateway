FROM amazoncorretto:21

COPY target/api-gateway-0.0.1-SNAPSHOT.jar /app/api-gateway-0.0.1-SNAPSHOT.jar

EXPOSE 8000

CMD ["java", "-jar", "/app/api-gateway-0.0.1-SNAPSHOT.jar"]