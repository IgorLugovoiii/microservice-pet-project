package com.example.session_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.example.session_service.clients")
public class SessionServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SessionServiceApplication.class, args);
	}

}
