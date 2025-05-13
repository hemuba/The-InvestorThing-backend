package com.investmate.backend.old;

import com.investmate.backend.common.exception.BadRequestException;
import com.investmate.backend.common.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class CurrentStocks_OLD {

    List<Stock_OLD> currentStockOLDS = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(CurrentStocks_OLD.class);


    public List<Stock_OLD> getAllStocks(){
        return this.currentStockOLDS;
    }

    public List<Stock_OLD> getStocksBy(String ticker, String sector) {
        boolean tickerInvalid = ticker == null || ticker.isBlank();
        boolean sectorInvalid = sector == null || sector.isBlank();

        if (tickerInvalid && sectorInvalid) {
            throw new BadRequestException("GET FAILED - You must provide at least one non-empty parameter: 'ticker' or 'sector'.");
        }

        List<Stock_OLD> result = currentStockOLDS.stream()
                .filter(s -> !tickerInvalid && s.getTicker().equalsIgnoreCase(ticker) || tickerInvalid)
                .filter(s -> !sectorInvalid && s.getSector().getDescription().equalsIgnoreCase(sector) || sectorInvalid)
                .toList();

        if (result.isEmpty()) {
            throw new NotFoundException("GET FAILED - No Stocks found with the given filters.");
        }

        return result;
    }

    public String removeStock(String ticker) {
        boolean removed = currentStockOLDS.removeIf(s -> s.getTicker().equalsIgnoreCase(ticker));
        if (removed) {
            logger.info("DELETE TICKER OK - Stock_OLD {} removed from the Database.", ticker.toUpperCase() );
            return "Ticker " + ticker.toUpperCase() + " successfully removed from Database.";
        }
        else{
            throw new NotFoundException("DELETE TICKER FAILED - Ticker " + ticker.toUpperCase() + " not found in the Database" );
        }
    }

}
