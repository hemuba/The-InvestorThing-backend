package com.stockmanager.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockManagerApplication {
    public StockManagerApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(StockManagerApplication.class, args);
    }
}
