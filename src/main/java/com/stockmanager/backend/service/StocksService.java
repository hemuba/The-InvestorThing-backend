package com.stockmanager.backend.service;


import com.stockmanager.backend.dto.StockDTOPatch;
import com.stockmanager.backend.dto.StockDTORequest;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.exception.NotFoundException;
import com.stockmanager.backend.model.Sectors;
import com.stockmanager.backend.model.Stock;
import com.stockmanager.backend.repository.CurrentStocks;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
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
            allStockDTO.add(new StockDTOResponse(
                    stock.getTicker(),
                    stock.getCompanyName(),
                    stock.getSector().getDescription(),
                    stock.getNoOfShares(),
                    stock.getPurchasePrice(),
                    stock.getCurrentPrice(),
                    stock.getCurrentReturn(),
                    stock.getCurrentReturnTotal(),
                    stock.getCurrentTotal(),
                    stock.getBuyDate()
            ));
        }
        logger.info("Fetched all stocks, total: {}", allStockDTO.size());
        return allStockDTO;
    }

    public List<StockDTOResponse> getStockBy(String ticker, String sector) {
        List<Stock> stocksBy = currentStocks.getStocksBy(ticker, sector);
        List<StockDTOResponse> stocksDTOBy = new ArrayList<>();
        for (Stock stock : stocksBy) {
            stocksDTOBy.add(new StockDTOResponse(
                    stock.getTicker(),
                    stock.getCompanyName(),
                    stock.getSector().getDescription(),
                    stock.getNoOfShares(),
                    stock.getPurchasePrice(),
                    stock.getCurrentPrice(),
                    stock.getCurrentReturn(),
                    stock.getCurrentReturnTotal(),
                    stock.getCurrentTotal(),
                    stock.getBuyDate()
            ));

        }
        logger.info("Total fetched stocks: {}", stocksDTOBy.size());
        return stocksDTOBy;
    }

    // POST METHODS //

    public StockDTOResponse addStock(StockDTORequest stockDTORequest) {
        BigDecimal currentReturn = BigDecimal.valueOf(stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentReturnTotal = BigDecimal.valueOf((stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice()) * stockDTORequest.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = BigDecimal.valueOf(stockDTORequest.getCurrentPrice() * stockDTORequest.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        Stock stock = new Stock(
                stockDTORequest.getTicker(),
                stockDTORequest.getCompanyName(),
                Sectors.fromDescription(stockDTORequest.getSector()),
                stockDTORequest.getNoOfShares(),
                stockDTORequest.getPurchasePrice(),
                stockDTORequest.getCurrentPrice(),
                currentReturn,
                currentReturnTotal,
                currentTotal,
                stockDTORequest.getBuyDate()
        );
        currentStocks.getAllStocks().add(stock);
        logger.info("Stock {} added to the database", stockDTORequest.getTicker());
        return new StockDTOResponse(
                stock.getTicker(),
                stock.getCompanyName(),
                stock.getSector().getDescription(),
                stock.getNoOfShares(),
                stock.getPurchasePrice(),
                stock.getCurrentPrice(),
                stock.getCurrentReturn(),
                stock.getCurrentReturnTotal(),
                stock.getCurrentTotal(),
                stock.getBuyDate()
        );

    }

    public List<StockDTOResponse> addStocks(List<StockDTORequest> stocksDTORequest) {
        List<StockDTOResponse> addedStocks = new ArrayList<>();
        for (StockDTORequest stockDTORequest : stocksDTORequest) {
            BigDecimal currentReturn = BigDecimal.valueOf(stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal currentReturnTotal = BigDecimal.valueOf((stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice()) * stockDTORequest.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal currentTotal = BigDecimal.valueOf(stockDTORequest.getCurrentPrice() * stockDTORequest.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
            Stock stock = new Stock(
                    stockDTORequest.getTicker(),
                    stockDTORequest.getCompanyName(),
                    Sectors.fromDescription(stockDTORequest.getSector()),
                    stockDTORequest.getNoOfShares(),
                    stockDTORequest.getPurchasePrice(),
                    stockDTORequest.getCurrentPrice(),
                    currentReturn,
                    currentReturnTotal,
                    currentTotal,
                    stockDTORequest.getBuyDate()
            );
            currentStocks.getAllStocks().add(stock);
            addedStocks.add(new StockDTOResponse(
                    stock.getTicker(),
                    stock.getCompanyName(),
                    stock.getSector().getDescription(),
                    stock.getNoOfShares(),
                    stock.getPurchasePrice(),
                    stock.getCurrentPrice(),
                    stock.getCurrentReturn(),
                    stock.getCurrentReturnTotal(),
                    stock.getCurrentTotal(),
                    stock.getBuyDate()
            ));
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
                return new StockDTOResponse(
                        stock.getTicker(),
                        stock.getCompanyName(),
                        stock.getSector().getDescription(),
                        stock.getNoOfShares(),
                        stock.getPurchasePrice(),
                        stock.getCurrentPrice(),
                        stock.getCurrentReturn(),
                        stock.getCurrentReturnTotal(),
                        stock.getCurrentTotal(),
                        stock.getBuyDate()
                );
            }

        }
        throw new NotFoundException("Ticker " + ticker.toUpperCase() + " not found in the Database");
    }

    // PATCH METHOD //

    public StockDTOResponse patchStock(String ticker, StockDTOPatch stockDTOPatch) {
        for (Stock stock : currentStocks.getAllStocks()) {
            if (stock.getTicker().equalsIgnoreCase(ticker)) {
                stock.setTicker(stockDTOPatch.getTicker() != null ? stockDTOPatch.getTicker() : stock.getTicker());
                stock.setCompanyName(stockDTOPatch.getCompanyName() != null ? stockDTOPatch.getCompanyName() : stock.getCompanyName());
                stock.setSector(stockDTOPatch.getSector() != null ? Sectors.fromDescription(stockDTOPatch.getSector()) : stock.getSector());
                stock.setPurchasePrice(stockDTOPatch.getPurchasePrice() != null ? stockDTOPatch.getPurchasePrice() : stock.getPurchasePrice());
                stock.setCurrentPrice(stockDTOPatch.getCurrentPrice() != null ? stockDTOPatch.getCurrentPrice() : stock.getCurrentPrice());
                stock.setCurrentReturn(stockDTOPatch.getCurrentPrice() != null && stockDTOPatch.getPurchasePrice() != null ?
                        BigDecimal.valueOf(stockDTOPatch.getCurrentPrice() - stockDTOPatch.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP) :
                        stock.getCurrentReturn());
                stock.setCurrentReturnTotal(
                        stockDTOPatch.getCurrentPrice() != null &&
                                stockDTOPatch.getPurchasePrice() != null &&
                                stockDTOPatch.getNoOfShares() != null ?
                                BigDecimal.valueOf(stockDTOPatch.getNoOfShares() * (stockDTOPatch.getCurrentPrice() - stockDTOPatch.getPurchasePrice())).setScale(2, RoundingMode.HALF_UP) :
                                stock.getCurrentReturnTotal()
                );
                stock.setCurrentTotal(
                        stockDTOPatch.getCurrentPrice() != null &&
                                stockDTOPatch.getNoOfShares() != null ?
                                BigDecimal.valueOf(stockDTOPatch.getCurrentPrice() * stockDTOPatch.getNoOfShares()).setScale(2, RoundingMode.HALF_UP) :
                                stock.getCurrentTotal()
                );
                stock.setBuyDate(stockDTOPatch.getBuyDate() != null ? stockDTOPatch.getBuyDate() : stock.getBuyDate());
                logger.info("Stock {} patched", stock.getTicker());
                return new StockDTOResponse(
                        stock.getTicker(),
                        stock.getCompanyName(),
                        stock.getSector().getDescription(),
                        stock.getNoOfShares(),
                        stock.getPurchasePrice(),
                        stock.getCurrentPrice(),
                        stock.getCurrentReturn(),
                        stock.getCurrentReturnTotal(),
                        stock.getCurrentTotal(),
                        stock.getBuyDate()
                );
            }
        }
        throw new NotFoundException("Ticker " + ticker.toUpperCase() + " not found in the Database");
    }
}
