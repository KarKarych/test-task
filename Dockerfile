FROM gradle:7.6-jdk17-alpine AS builder
WORKDIR /app
COPY . /app
RUN gradle bootJar

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/relex-task-1.0.jar /app/rt.jar

ENTRYPOINT ["java"]
CMD ["-jar", "rt.jar"]
