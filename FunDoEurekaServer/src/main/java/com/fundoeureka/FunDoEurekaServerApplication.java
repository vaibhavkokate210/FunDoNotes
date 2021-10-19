package com.fundoeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FunDoEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunDoEurekaServerApplication.class, args);
	}

}
