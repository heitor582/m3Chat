FROM openjdk:17-oracle AS TEMP_BUILD_IMAGE

RUN microdnf install findutils

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle

RUN ./gradlew build
COPY . .
RUN ./gradlew build -x test

FROM openjdk:17-oracle

ENV ARTIFACT_NAME=application.jar
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME

COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
RUN ls -la
EXPOSE 8080
EXPOSE 10400
CMD java -jar ./$ARTIFACT_NAME --spring.profiles.active=production -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:10400