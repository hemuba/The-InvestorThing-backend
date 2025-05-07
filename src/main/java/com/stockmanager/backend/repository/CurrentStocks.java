package com.stockmanager.backend.repository;

import com.stockmanager.backend.exception.BadRequestException;
import com.stockmanager.backend.exception.NotFoundException;
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

    public List<Stock> getStocksBy(String ticker, String sector) {
        boolean tickerInvalid = ticker == null || ticker.isBlank();
        boolean sectorInvalid = sector == null || sector.isBlank();

        if (tickerInvalid && sectorInvalid) {
            throw new BadRequestException("You must provide at least one non-empty parameter: 'ticker' or 'sector'.");
        }

        List<Stock> result = currentStocks.stream()
                .filter(s -> !tickerInvalid && s.getTicker().equalsIgnoreCase(ticker) || tickerInvalid)
                .filter(s -> !sectorInvalid && s.getSector().getDescription().equalsIgnoreCase(sector) || sectorInvalid)
                .toList();

        if (result.isEmpty()) {
            throw new NotFoundException("No stock found with the given filters.");
        }

        return result;
    }

    public String removeStock(String ticker) {
        boolean removed = currentStocks.removeIf(s -> s.getTicker().equalsIgnoreCase(ticker));
        if (removed) {
            return "Ticker " + ticker.toUpperCase() + " successfully removed from Database.";
        }
        else{
            throw new NotFoundException("Ticker " + ticker + " not found in the Database" );
        }
    }

}
