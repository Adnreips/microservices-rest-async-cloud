package com.springboot.microservice.forex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@EnableEurekaClient
@EnableAsync
public class DemoSpringBootForexServiceApplication {

	public static void main(String[] args) {
		ApplicationContext context= SpringApplication.run(
				DemoSpringBootForexServiceApplication.class, args);

	}
//
//	@Bean
//	public Executor taskExecutor() {
//
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(2);
//		executor.setMaxPoolSize(2);
//		executor.setQueueCapacity(500);
//		executor.setWaitForTasksToCompleteOnShutdown(true);
//		executor.initialize();
//		return executor;
//	}


}
