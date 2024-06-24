package com.ahoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class VendedoresApplication {

	private static final Logger log = (Logger) org.slf4j.LoggerFactory.getLogger(VendedoresApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VendedoresApplication.class, args);
	}

}
