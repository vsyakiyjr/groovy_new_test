FROM gradle:7.2.0-jdk17

ADD build/libs/test14-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar", "app.jar"]
