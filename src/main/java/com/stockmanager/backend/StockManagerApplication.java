package com.stockmanager.backend;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class
StockManagerApplication {
    private final Logger logger = LoggerFactory.getLogger(StockManagerApplication.class);

    @Value(("${stockmanager.author}"))
    private String author;

    @Value("${stockmanager.version}")
    private String version;

    @Value("${stockmanager.module}")
    private String module;

    public StockManagerApplication() {


    }

    public static void main(String[] args) {
        SpringApplication.run(StockManagerApplication.class, args);
    }
    @PostConstruct
    private void postConstruct(){
        logger.info("Application info - Module: {} | Version: {} | Author: {}", module, version, author);
    }

}
