package com.stockmanager.backend;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class StockManagerApplication {

	private static Logger logger = LoggerFactory.getLogger(StockManagerApplication.class);

	@Value("${stockmanager.version}")
	private String version;

	@Value("${stockmanager.author}")
	private String author;

	@Value("${stockmanager.module}")
	private String module;

	public static void main(String[] args) {
		SpringApplication.run(StockManagerApplication.class, args);
	}

	@PostConstruct
	private void postCostruct(){
		logger.info("Application Info | Version: {} - Module: {} - Author: {}", version, module, author);

	}

}
