package com.fundouser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableEurekaClient
public class FunDoUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunDoUserApplication.class, args);
	}
}
