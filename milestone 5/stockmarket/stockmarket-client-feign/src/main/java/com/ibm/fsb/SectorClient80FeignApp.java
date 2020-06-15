package com.ibm.fsb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.ibm.fsb"})
@ComponentScan("com.ibm.fsb")
public class SectorClient80FeignApp {

	public static void main(String[] args) {
		SpringApplication.run(SectorClient80FeignApp.class, args);
	}

}
