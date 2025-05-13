package com.investmate.backend;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class InvestMateApplication {

	private static final Logger logger = LoggerFactory.getLogger(InvestMateApplication.class);

	@Value("${investmate.version}")
	private String version;

	@Value("${investmate.author}")
	private String author;

	@Value("${investmate.module}")
	private String module;

	public static void main(String[] args) {
		SpringApplication.run(InvestMateApplication.class, args);
	}

	@PostConstruct
	private void postCostruct(){
		logger.info("Application Info | Version: {} - Module: {} - Author: {}", version, module, author);

	}

}
