# Base Image
FROM openjdk:8-jdk-alpine
# Arg - variable during build time
ARG DEPENDENCY=target/dependency
# Port in which the container is exposed not host
EXPOSE 8100
#Efficient caching
# copy dependency into the app
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
# copy classes into the application folder directly for execution
COPY ${DEPENDENCY}/BOOT-INF/classes /app
# ENTRYPOINT - first command to be executed when the container starts
ENTRYPOINT ["java","-cp","app:app/lib/*","com.css.unitconversion.Application"]