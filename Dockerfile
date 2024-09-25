FROM    openjdk:17-jdk
LABEL   authors="Taewoo-HN"

COPY    build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
