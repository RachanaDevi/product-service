FROM openjdk:19-jdk-alpine as build
WORKDIR /Users/rachanadevi/Coding/ArchitecturalKatas/product-service
COPY . .
RUN ./gradlew clean build

FROM openjdk:19-jdk-alpine
COPY --from=build /Users/rachanadevi/Coding/ArchitecturalKatas/product-service/build/libs/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]