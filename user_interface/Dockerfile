FROM gradle:6.8.3-jdk11 as build

USER root


COPY --chown=gradle:gradle . /IdeaProjects/base_project
WORKDIR  /IdeaProjects/base_project
RUN gradle build --stacktrace

FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8080

ENV LD_LIBRARY_PATH /usr/lib
COPY --from=build /IdeaProjects/base_project/user_interface/build/libs/user_interface-0.0.1-SNAPSHOT.jar app.jar
CMD java -Xmx100m -jar /app.jar