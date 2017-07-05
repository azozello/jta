package com.jta.shop;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO: Test UserService methods
 */
@SpringBootApplication
public class JtaApplication {

	private final static Logger logger = Logger.getLogger(JtaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JtaApplication.class, args);
	}

	public static Logger getLogger() {
		return logger;
	}
}
