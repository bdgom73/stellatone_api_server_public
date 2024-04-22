FROM openjdk:17

WORKDIR /app/music-api

COPY build/libs/*.jar /app/music-api/music-api.jar

ENV PORT=8080

CMD [
    "java",
    "-jar",
    "music-api.jar",
    "--spring.config.location=classpath:/application.yml,/app/music-api/application-prod.yml",
    "--spring.profiles.active=prod",
     "--server.port=${PORT}"
]

