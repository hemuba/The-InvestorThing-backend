package com.stockmanager.backend.controller;


import com.stockmanager.backend.model.Stocks;
import com.stockmanager.backend.response.ApiResponse;
import com.stockmanager.backend.service.StockServiceJpa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("stocks-jpa")
public class ControllerJpa {

    private final StockServiceJpa stockServiceJpa;

    public ControllerJpa(StockServiceJpa stockServiceJpa) {
        this.stockServiceJpa = stockServiceJpa;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllStocks(){
        List<Stocks> allStocks = stockServiceJpa.getAllStocks();
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stocks", allStocks));
    }
}
