package com.stockmanager.backend.service;


import com.stockmanager.backend.model.Stocks;
import com.stockmanager.backend.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceJpa {

    private final StockRepository stockRepository;

    public StockServiceJpa(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stocks> getAllStocks(){
        return stockRepository.findAll();
    }
}
