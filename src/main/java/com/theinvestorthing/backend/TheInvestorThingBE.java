package com.theinvestorthing.backend;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class TheInvestorThingBE {

	private static final Logger logger = LoggerFactory.getLogger(TheInvestorThingBE.class);

	@Value("${spring.theinvestorthing.name}")
	private String name;

	@Value("${spring.theinvestorthing.version}")
	private String version;

	@Value("${spring.theinvestorthing.author}")
	private String author;

	@Value("${spring.theinvestorthing.module}")
	private String module;

	public static void main(String[] args) {
		SpringApplication.run(TheInvestorThingBE.class, args);
	}

	@PostConstruct
	private void appInfo(){
		logger.info("Application Info - Name: {} | Module: {} | Version: {} | Author: {}", name, module, version, author);

	}

}
