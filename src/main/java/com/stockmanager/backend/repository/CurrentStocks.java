package com.stockmanager.backend.repository;

import com.stockmanager.backend.model.Stock;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CurrentStocks {

    List<Stock> currentStocks = new ArrayList<>();


    public List<Stock> getAllStocks(){
        return this.currentStocks;
    }

    public List<Stock> getStocksBy(String ticker, String sector){
        return currentStocks.stream()
                .filter(s -> ticker == null || s.getTicker().equalsIgnoreCase(ticker))
                .filter(s -> sector == null || s.getSector().getDescription().equalsIgnoreCase(sector))
                .toList();
    }

    public String removeStock(String ticker){
        currentStocks.removeIf(s -> s.getTicker().equalsIgnoreCase(ticker));
        return "Ticker " + ticker + " successfully removed from Database.";
    }

}
