FROM amazoncorretto:11-al2-jdk as builder

WORKDIR /opt/build

ARG MAVEN_CLI_OPTS
ARG MAVEN_OPTS
ARG MAVEN_USER
ARG MAVEN_PASSWORD

COPY .m2 .m2
COPY mvnw .
COPY .mvn .mvn

RUN chmod +x ./mvnw
COPY src src
COPY pom.xml .

#MAVEN INSTALL
RUN ./mvnw -DskipTests $MAVEN_CLI_OPTS $MAVEN_OPTS install
RUN mkdir -p target/extracted && java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted

FROM amazoncorretto:11-al2-jdk as base
ARG BUILD=/opt/build/target/extracted

RUN mkdir -m777 /opt/apps && \
    mkdir -m777 /opt/config

RUN yum update
RUN yum upgrade

# Copy the application layers
COPY --from=builder ${BUILD}/dependencies/ /opt/apps/
COPY --from=builder ${BUILD}/spring-boot-loader/ /opt/apps/
COPY --from=builder ${BUILD}/snapshot-dependencies/ /opt/apps/
COPY --from=builder ${BUILD}/application/ /opt/apps/

FROM base

EXPOSE 8089

WORKDIR /opt/apps

HEALTHCHECK NONE

CMD ["java", "org.springframework.boot.loader.JarLauncher"]
