package com.orderapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrderappApplication {
	
	private static Logger log = LogManager.getLogger(OrderappApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(OrderappApplication.class, args);
		log.info("------------------- Application Started -------------- ");
	}

}
