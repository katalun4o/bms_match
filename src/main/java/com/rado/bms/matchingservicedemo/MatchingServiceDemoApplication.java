package com.rado.bms.matchingservicedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MatchingServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchingServiceDemoApplication.class, args);
	}

}
