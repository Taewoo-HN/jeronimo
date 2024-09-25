FROM gradle:8-jdk17 AS builder
LABEL   authors="Taewoo-HN"
WORKDIR /app
COPY . .

# Gradle로 빌드하여 JAR 파일 생성
RUN ./gradlew clean build -x test

# 실행 단계: Distroless 이미지 사용
FROM gcr.io/distroless/java17-debian11:nonroot
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# Spring Boot 기본 포트
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

