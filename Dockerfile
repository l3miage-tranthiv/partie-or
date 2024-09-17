FROM maven AS build
WORKDIR /intégrator-project-planner
COPY . .
RUN mvn clean install


FROM openjdk:21 AS deploy
LABEL description="Java 21 Docker image build to run Integrator project planner"

ARG JAR_FILE="/intégrator-project-planner/server/target/integrator-project-planner.jar"
ENV TZ="Europe/Paris"

COPY --from=build ${JAR_FILE} /opt/app/app.jar
RUN chmod u+x /opt/app/app.jar

WORKDIR /opt/app

EXPOSE 4201

CMD ["java", "-jar", "/opt/app/app.jar"]