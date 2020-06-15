FROM java:8-jdk-alpine

COPY ./target/provider-hystrix-8001-1.0.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch provider-hystrix-8001-1.0.jar'

ENTRYPOINT ["java", "-jar", "provider-hystrix-8001-1.0.jar"]
