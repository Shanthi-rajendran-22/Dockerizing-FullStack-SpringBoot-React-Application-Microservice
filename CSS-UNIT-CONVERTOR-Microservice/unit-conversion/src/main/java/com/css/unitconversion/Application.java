package com.css.unitconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.css.unitconversion.serviceproxy")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}