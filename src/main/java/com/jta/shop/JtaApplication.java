package com.jta.shop;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
