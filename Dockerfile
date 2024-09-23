FROM    openjdk:17-jdk
LABEL   authors="Taewoo-HN"

COPY    build/libs/JERONIMO-0.0.1-TEST.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
