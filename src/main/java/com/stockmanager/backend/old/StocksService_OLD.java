package com.stockmanager.backend.old;


import com.stockmanager.backend.exception.BadRequestException;
import com.stockmanager.backend.exception.MultiStatusException;
import com.stockmanager.backend.exception.NotFoundException;
import com.stockmanager.backend.exception.UnprocessableEntityException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Service
public class StocksService_OLD {

    private final CurrentStocks_OLD currentStocksOLD;
    private static final Logger logger = LoggerFactory.getLogger(StocksService_OLD.class);

    public StocksService_OLD(CurrentStocks_OLD currentStocksOLD) {
        this.currentStocksOLD = currentStocksOLD;
    }

    // GET METHODS //

    public List<StockDTOResponse_OLD> getAllStocks() {
        List<Stock_OLD> allStockOLDS = currentStocksOLD.getAllStocks();
        List<StockDTOResponse_OLD> allStockDTO = new ArrayList<>();

        for (Stock_OLD stock_old : allStockOLDS) {
            allStockDTO.add(StockMapper_OLD.toResponse(stock_old));
        }
        logger.info("Fetched all stocks, total: {}", allStockDTO.size());
        return allStockDTO;
    }

    public List<StockDTOResponse_OLD> getStockBy(String ticker, String sector) {
        List<Stock_OLD> stocksBy = currentStocksOLD.getStocksBy(ticker, sector);
        List<StockDTOResponse_OLD> stocksDTOBy = new ArrayList<>();
        for (Stock_OLD stock_OLD : stocksBy) {
            stocksDTOBy.add(StockMapper_OLD.toResponse(stock_OLD));
        }
        logger.info("Total fetched stocks: {}", stocksDTOBy.size());
        return stocksDTOBy;
    }

    // POST METHODS //

    public StockDTOResponse_OLD addStock(StockDTORequest_OLD stockDTORequestOLD) {

        Set<String> existingTickers = currentStocksOLD.getAllStocks().stream()
                .map(s -> s.getTicker().toLowerCase())
                .collect(Collectors.toSet());
        if (existingTickers.contains(stockDTORequestOLD.getTicker().toLowerCase())){
            throw new BadRequestException("POST FAILED - Stock_OLD " + stockDTORequestOLD.getTicker() + " not added to the database as it already exists. Try PUT or PATCH methods instead");
        }

        Stock_OLD stockOLD = StockMapper_OLD.toEntity(stockDTORequestOLD);
        currentStocksOLD.getAllStocks().add(stockOLD);
        logger.info("POST OK - Stock_OLD {} added to the database", stockDTORequestOLD.getTicker());
        return StockMapper_OLD.toResponse(stockOLD);

    }

    public List<StockDTOResponse_OLD> addStocks(List<StockDTORequest_OLD> stocksDTORequest) {

        Set<String> existingTickersLower = currentStocksOLD.getAllStocks().stream()
                .map(s -> s.getTicker().toLowerCase())
                .collect(Collectors.toSet());

        List<StockDTOResponse_OLD> addedStocks = new ArrayList<>();
        for (StockDTORequest_OLD stockDTORequestOLD : stocksDTORequest) {
            String tickerLow = stockDTORequestOLD.getTicker().toLowerCase();
            if (existingTickersLower.contains(tickerLow)){
                logger.warn("POST FAILED - Stock_OLD {} not added to the database as it already exists. Try PUT or PATCH methods instead", stockDTORequestOLD.getTicker().toUpperCase());
                continue;
            }
            logger.info("POST OK - Stock_OLD {} added to the Database ", stockDTORequestOLD.getTicker());
            Stock_OLD stockOLD = StockMapper_OLD.toEntity(stockDTORequestOLD);
            currentStocksOLD.getAllStocks().add(stockOLD);
            StockDTOResponse_OLD stockResponse = StockMapper_OLD.toResponse(stockOLD);
            addedStocks.add(stockResponse);
        }
        if (addedStocks.isEmpty()) {
            throw new UnprocessableEntityException("POST BATCH FAILED - The request is properly formatted but it cannot be completed at this time.");
        } else if (addedStocks.size() < stocksDTORequest.size()) {
            throw new MultiStatusException("POST BATCH PARTIALLY FAILED - Not all the stocks requested have been added to the Database, requested: " + stocksDTORequest.size() + " -> added: " + addedStocks.size());

        }
        logger.info("POST BATCH OK - Total stocks added to the Database: {}", addedStocks.size());
        return addedStocks;
    }


    // DELETE METHODS //

    public String removeStock(String ticker) {
        return currentStocksOLD.removeStock(ticker);
    }



    // UPDATE METHOD //

    public StockDTOResponse_OLD updateStock(String ticker, StockDTORequest_OLD stockDTORequestOLD) {
        for (Stock_OLD stockOLD : currentStocksOLD.getAllStocks()) {
            if (stockOLD.getTicker().equalsIgnoreCase(ticker)) {
               StockMapper_OLD.updateStock(stockOLD, stockDTORequestOLD);
                logger.info("PUT OK - Stock_OLD {} updated", stockOLD.getTicker());
                return StockMapper_OLD.toResponse(stockOLD);
            }
        }
        throw new NotFoundException("PUT FAILED - Ticker " + ticker.toUpperCase() + " not found in the Database");
    }

    // PATCH METHOD //

    public StockDTOResponse_OLD patchStock(String ticker, StockDTOPatch_OLD stockDTOPatchOLD) {
        for (Stock_OLD stockOLD : currentStocksOLD.getAllStocks()) {
            if (stockOLD.getTicker().equalsIgnoreCase(ticker)) {
                StockMapper_OLD.patchStock(stockOLD, stockDTOPatchOLD);
                logger.info("Stock_OLD {} patched", stockOLD.getTicker());
                return StockMapper_OLD.toResponse(stockOLD);
            }
        }
        throw new NotFoundException("PATCH FAILED - Ticker " + ticker.toUpperCase() + " not found in the Database");
    }
}
