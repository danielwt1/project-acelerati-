FROM arm64v8/amazoncorretto:17

ENV LANG en_US.UTF-8
ENV LC_ALL en_US.UTF-8
ENV TZ=America/Bogota
VOLUME /tmp

ARG PROJECT_NAME
COPY ./build/libs/${PROJECT_NAME}-0.0.1-SNAPSHOT.jar app.jar

ARG PORT
EXPOSE ${PORT}

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=America/Bogota","-jar","/app.jar"]