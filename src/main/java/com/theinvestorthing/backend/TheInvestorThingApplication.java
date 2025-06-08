package com.theinvestorthing.backend;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class TheInvestorThingApplication {

	private static final Logger logger = LoggerFactory.getLogger(TheInvestorThingApplication.class);

	@Value("${spring.theinvestorthing.version}")
	private String version;

	@Value("${spring.theinvestorthing.author}")
	private String author;

	@Value("${spring.theinvestorthing.module}")
	private String module;

	public static void main(String[] args) {
		SpringApplication.run(TheInvestorThingApplication.class, args);
	}

	@PostConstruct
	private void appInfo(){
		logger.info("Application Info - Module: {} | Version: {} | Author: {}",  module, version, author);

	}

}
