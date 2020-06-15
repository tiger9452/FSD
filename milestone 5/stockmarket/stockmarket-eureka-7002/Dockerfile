FROM java:8-jdk-alpine

COPY ./target/eureka-7002-1.0.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch eureka-7002-1.0.jar'

ENTRYPOINT ["java", "-jar", "eureka-7002-1.0.jar"]
