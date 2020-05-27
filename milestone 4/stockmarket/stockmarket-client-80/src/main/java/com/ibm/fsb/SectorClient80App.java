package com.ibm.fsb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.ibm.myrule.MySelfRule;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "STOCKMARKET-CLIENT", configuration = MySelfRule.class)
public class SectorClient80App {

	public static void main(String[] args) {
		SpringApplication.run(SectorClient80App.class, args);

	}

}
