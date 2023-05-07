FROM openjdk:8

VOLUME /cloud-user-info

ADD target/cloud-user-info-1.0-SNAPSHOT.jar app.jar

EXPOSE 8002

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=cloud-cloud-user-info-ml", "--server.port=8002", "> /log/app.log"]