package com.stockmanager.backend.service;


import com.stockmanager.backend.dto.StockDTOPatch;
import com.stockmanager.backend.dto.StockDTORequest;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.exception.BadRequestException;
import com.stockmanager.backend.exception.NotFoundException;
import com.stockmanager.backend.mapper.StockMapper;
import com.stockmanager.backend.model.Sectors;
import com.stockmanager.backend.model.Stock;
import com.stockmanager.backend.repository.CurrentStocks;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Service
public class StocksService {

    private final CurrentStocks currentStocks;
    private final Logger logger = LoggerFactory.getLogger(StocksService.class);

    public StocksService(CurrentStocks currentStocks) {
        this.currentStocks = currentStocks;
    }

    // GET METHODS //

    public List<StockDTOResponse> getAllStocks() {
        List<Stock> allStocks = currentStocks.getAllStocks();
        List<StockDTOResponse> allStockDTO = new ArrayList<>();

        for (Stock stock : allStocks) {
            allStockDTO.add(StockMapper.toResponse(stock));
        }
        logger.info("Fetched all stocks, total: {}", allStockDTO.size());
        return allStockDTO;
    }

    public List<StockDTOResponse> getStockBy(String ticker, String sector) {
        List<Stock> stocksBy = currentStocks.getStocksBy(ticker, sector);
        List<StockDTOResponse> stocksDTOBy = new ArrayList<>();
        for (Stock stock : stocksBy) {
            stocksDTOBy.add(StockMapper.toResponse(stock));
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
            throw new BadRequestException("Stock " + stockDTORequest.getTicker() + "not added to the database as it already exists. Try PUT or PATCH methods instead");
        }

        Stock stock = StockMapper.toEntity(stockDTORequest);
        currentStocks.getAllStocks().add(stock);
        logger.info("Stock {} added to the database", stockDTORequest.getTicker());
        return StockMapper.toResponse(stock);

    }

    public List<StockDTOResponse> addStocks(List<StockDTORequest> stocksDTORequest) {

        Set<String> existingTickersLower = currentStocks.getAllStocks().stream()
                .map(s -> s.getTicker().toLowerCase())
                .collect(Collectors.toSet());

        List<StockDTOResponse> addedStocks = new ArrayList<>();
        for (StockDTORequest stockDTORequest : stocksDTORequest) {
            if (existingTickersLower.contains(stockDTORequest.getTicker().toLowerCase())){
                logger.warn("Stock {} not added to the database as it already exists. Try PUT or PATCH methods instead", stockDTORequest.getTicker());
                continue;
            }

            Stock stock = StockMapper.toEntity(stockDTORequest);
            currentStocks.getAllStocks().add(stock);
            StockDTOResponse stockResponse = StockMapper.toResponse(stock);
            addedStocks.add(stockResponse);
        }
        logger.info("Total stocks added to the Database: {}", addedStocks.size());
        return addedStocks;
    }


    // DELETE METHODS //

    public String removeStock(String ticker) {
        logger.info("Stock {} removed from the Database", ticker.toUpperCase());
        return currentStocks.removeStock(ticker);
    }



    // UPDATE METHOD //

    public StockDTOResponse updateStock(String ticker, StockDTORequest stockDTORequest) {
        BigDecimal currentReturn = BigDecimal.valueOf(stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentReturnTotal = BigDecimal.valueOf((stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice()) * stockDTORequest.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = BigDecimal.valueOf(stockDTORequest.getCurrentPrice() * stockDTORequest.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        for (Stock stock : currentStocks.getAllStocks()) {
            if (stock.getTicker().equalsIgnoreCase(ticker)) {
                stock.setTicker(stockDTORequest.getTicker());
                stock.setCompanyName(stockDTORequest.getCompanyName());
                stock.setSector(Sectors.fromDescription(stockDTORequest.getSector()));
                stock.setNoOfShares(stockDTORequest.getNoOfShares());
                stock.setPurchasePrice(stockDTORequest.getPurchasePrice());
                stock.setCurrentPrice(stockDTORequest.getCurrentPrice());
                stock.setCurrentReturn(currentReturn);
                stock.setCurrentReturnTotal(currentReturnTotal);
                stock.setCurrentTotal(currentTotal);
                stock.setBuyDate(stockDTORequest.getBuyDate());
                logger.info("Stock {} updated", stock.getTicker());
                return StockMapper.toResponse(stock);
            }

        }
        throw new NotFoundException("Ticker " + ticker.toUpperCase() + " not found in the Database");
    }

    // PATCH METHOD //

    public StockDTOResponse patchStock(String ticker, StockDTOPatch stockDTOPatch) {
        for (Stock stock : currentStocks.getAllStocks()) {
            if (stock.getTicker().equalsIgnoreCase(ticker)) {
                StockMapper.patchStock(stock, stockDTOPatch);
                logger.info("Stock {} patched", stock.getTicker());
                return StockMapper.toResponse(stock);
            }
        }
        throw new NotFoundException("Ticker " + ticker.toUpperCase() + " not found in the Database");
    }
}
