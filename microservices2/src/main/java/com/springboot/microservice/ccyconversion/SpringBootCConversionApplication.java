package com.springboot.microservice.ccyconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients("com.springboot.microservice.ccyconversion")
@EnableDiscoveryClient
public class SpringBootCConversionApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCConversionApplication.class, args);
	}
}
