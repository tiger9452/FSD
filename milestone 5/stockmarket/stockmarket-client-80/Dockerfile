FROM java:8-jdk-alpine

COPY ./target/client-80-1.0.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch client-80-1.0.jar'

ENTRYPOINT ["java", "-jar", "client-80-1.0.jar"]
