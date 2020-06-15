FROM java:8-jdk-alpine

COPY ./target/client-feign-1.0.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch client-feign-1.0.jar'

ENTRYPOINT ["java", "-jar", "client-feign-1.0.jar"]
