package com.example.configclient;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log
@SpringBootApplication
public class ConfigClientApplication implements CommandLineRunner {

	@Value("${vars.variable_one.value}")
	private String testValue;

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Test value - " + testValue);
	}
}
