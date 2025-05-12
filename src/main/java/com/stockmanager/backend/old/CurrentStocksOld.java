package com.stockmanager.backend.old;

import com.stockmanager.backend.exception.BadRequestException;
import com.stockmanager.backend.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class CurrentStocksOld {

    List<StockOld> currentStockOlds = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(CurrentStocksOld.class);


    public List<StockOld> getAllStocks(){
        return this.currentStockOlds;
    }

    public List<StockOld> getStocksBy(String ticker, String sector) {
        boolean tickerInvalid = ticker == null || ticker.isBlank();
        boolean sectorInvalid = sector == null || sector.isBlank();

        if (tickerInvalid && sectorInvalid) {
            throw new BadRequestException("GET FAILED - You must provide at least one non-empty parameter: 'ticker' or 'sector'.");
        }

        List<StockOld> result = currentStockOlds.stream()
                .filter(s -> !tickerInvalid && s.getTicker().equalsIgnoreCase(ticker) || tickerInvalid)
                .filter(s -> !sectorInvalid && s.getSector().getDescription().equalsIgnoreCase(sector) || sectorInvalid)
                .toList();

        if (result.isEmpty()) {
            throw new NotFoundException("GET FAILED - No Stock found with the given filters.");
        }

        return result;
    }

    public String removeStock(String ticker) {
        boolean removed = currentStockOlds.removeIf(s -> s.getTicker().equalsIgnoreCase(ticker));
        if (removed) {
            logger.info("DELETE TICKER OK - Stock {} removed from the Database.", ticker.toUpperCase() );
            return "Ticker " + ticker.toUpperCase() + " successfully removed from Database.";
        }
        else{
            throw new NotFoundException("DELETE TICKER FAILED - Ticker " + ticker.toUpperCase() + " not found in the Database" );
        }
    }

}
