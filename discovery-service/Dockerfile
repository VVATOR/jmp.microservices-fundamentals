FROM amazoncorretto:17-alpine  AS corretto-jdk
# required for strip-debug to work
RUN apk add --no-cache binutils
# Build small JRE image
RUN jlink \
         --add-modules ALL-MODULE-PATH \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /jre

FROM alpine:latest AS base-image
ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"
COPY --from=corretto-jdk /jre $JAVA_HOME

### Applications images
FROM base-image AS app
WORKDIR /app
COPY ./target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]