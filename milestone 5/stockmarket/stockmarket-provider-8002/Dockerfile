FROM java:8-jdk-alpine

COPY ./target/provider-8002-1.0.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch provider-8002-1.0.jar'

ENTRYPOINT ["java", "-jar", "provider-8002-1.0.jar"]
