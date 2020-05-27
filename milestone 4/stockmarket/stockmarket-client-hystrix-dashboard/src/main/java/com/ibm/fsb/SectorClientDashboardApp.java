package com.ibm.fsb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
//@EnableCircuitBreaker
public class SectorClientDashboardApp {

	public static void main(String[] args) {
		SpringApplication.run(SectorClientDashboardApp.class, args);
	}

}
