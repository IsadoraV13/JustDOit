package com.justdoit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JustDOit {

	private static final Logger log = LoggerFactory.getLogger(JustDOit.class);

	public static void main(String[] args) {

		// tell Spring Boot to start application, create a servlet container and host application in servlet container
		SpringApplication.run(JustDOit.class, args);
		log.info("Start");
	}
}
