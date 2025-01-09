FROM openjdk:17-jdk-alpine AS builder
WORKDIR /build
RUN apk add --no-cache maven
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests && cp target/*.jar app.jar

FROM openjdk:17-jdk-alpine AS runtime
WORKDIR /app
COPY --from=builder /build/app.jar .
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]