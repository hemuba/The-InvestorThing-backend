package com.stockmanager.backend.old;


import com.stockmanager.backend.dto.StockDTOPatch;
import com.stockmanager.backend.dto.StockDTORequest;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.exception.BadRequestException;
import com.stockmanager.backend.exception.MultiStatusException;
import com.stockmanager.backend.exception.NotFoundException;
import com.stockmanager.backend.exception.UnprocessableEntityException;
import com.stockmanager.backend.mapper.StockMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Service
public class StocksServiceOld {

    private final CurrentStocksOld currentStocks;
    private static final Logger logger = LoggerFactory.getLogger(StocksServiceOld.class);

    public StocksServiceOld(CurrentStocksOld currentStocks) {
        this.currentStocks = currentStocks;
    }

    // GET METHODS //

    public List<StockDTOResponse> getAllStocks() {
        List<StockOld> allStockOlds = currentStocks.getAllStocks();
        List<StockDTOResponse> allStockDTO = new ArrayList<>();

        for (StockOld stockOld : allStockOlds) {
            allStockDTO.add(StockMapper.toResponse(stockOld));
        }
        logger.info("Fetched all stocks, total: {}", allStockDTO.size());
        return allStockDTO;
    }

    public List<StockDTOResponse> getStockBy(String ticker, String sector) {
        List<StockOld> stocksBy = currentStocks.getStocksBy(ticker, sector);
        List<StockDTOResponse> stocksDTOBy = new ArrayList<>();
        for (StockOld stockOld : stocksBy) {
            stocksDTOBy.add(StockMapper.toResponse(stockOld));
        }
        logger.info("Total fetched stocks: {}", stocksDTOBy.size());
        return stocksDTOBy;
    }

    // POST METHODS //

    public StockDTOResponse addStock(StockDTORequest stockDTORequest) {

        Set<String> existingTickers = currentStocks.getAllStocks().stream()
                .map(s -> s.getTicker().toLowerCase())
                .collect(Collectors.toSet());
        if (existingTickers.contains(stockDTORequest.getTicker().toLowerCase())){
            throw new BadRequestException("POST FAILED - Stock " + stockDTORequest.getTicker() + " not added to the database as it already exists. Try PUT or PATCH methods instead");
        }

        StockOld stockOld = StockMapper.toEntity(stockDTORequest);
        currentStocks.getAllStocks().add(stockOld);
        logger.info("POST OK - Stock {} added to the database", stockDTORequest.getTicker());
        return StockMapper.toResponse(stockOld);

    }

    public List<StockDTOResponse> addStocks(List<StockDTORequest> stocksDTORequest) {

        Set<String> existingTickersLower = currentStocks.getAllStocks().stream()
                .map(s -> s.getTicker().toLowerCase())
                .collect(Collectors.toSet());

        List<StockDTOResponse> addedStocks = new ArrayList<>();
        for (StockDTORequest stockDTORequest : stocksDTORequest) {
            String tickerLow = stockDTORequest.getTicker().toLowerCase();
            if (existingTickersLower.contains(tickerLow)){
                logger.warn("POST FAILED - Stock {} not added to the database as it already exists. Try PUT or PATCH methods instead", stockDTORequest.getTicker().toUpperCase());
                continue;
            }
            logger.info("POST OK - Stock {} added to the Database ", stockDTORequest.getTicker());
            StockOld stockOld = StockMapper.toEntity(stockDTORequest);
            currentStocks.getAllStocks().add(stockOld);
            StockDTOResponse stockResponse = StockMapper.toResponse(stockOld);
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
        return currentStocks.removeStock(ticker);
    }



    // UPDATE METHOD //

    public StockDTOResponse updateStock(String ticker, StockDTORequest stockDTORequest) {
        for (StockOld stockOld : currentStocks.getAllStocks()) {
            if (stockOld.getTicker().equalsIgnoreCase(ticker)) {
               StockMapper.updateStock(stockOld, stockDTORequest);
                logger.info("PUT OK - Stock {} updated", stockOld.getTicker());
                return StockMapper.toResponse(stockOld);
            }
        }
        throw new NotFoundException("PUT FAILED - Ticker " + ticker.toUpperCase() + " not found in the Database");
    }

    // PATCH METHOD //

    public StockDTOResponse patchStock(String ticker, StockDTOPatch stockDTOPatch) {
        for (StockOld stockOld : currentStocks.getAllStocks()) {
            if (stockOld.getTicker().equalsIgnoreCase(ticker)) {
                StockMapper.patchStock(stockOld, stockDTOPatch);
                logger.info("Stock {} patched", stockOld.getTicker());
                return StockMapper.toResponse(stockOld);
            }
        }
        throw new NotFoundException("PATCH FAILED - Ticker " + ticker.toUpperCase() + " not found in the Database");
    }
}
