package com.stockmanager.backend.service;


import com.stockmanager.backend.dto.StockDTOPatch;
import com.stockmanager.backend.dto.StockDTORequest;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.exceptions.BadRequestException;
import com.stockmanager.backend.exceptions.NotFoundException;
import com.stockmanager.backend.model.Sectors;
import com.stockmanager.backend.model.Stock;
import com.stockmanager.backend.repository.StocksRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Service
public class StockService {

    private final StocksRepository stocksRepository;
    private final Logger logger = LoggerFactory.getLogger(StockService.class);

    public StockService(StocksRepository stocksRepository) {
        this.stocksRepository = stocksRepository;
    }

    public List<StockDTOResponse> getAllStocks() {
        List<Stock> allStocks = stocksRepository.getAllStocks();
        List<StockDTOResponse> allStocksDTO = new ArrayList<>();
        for (Stock stock : allStocks) {
            allStocksDTO.add(new StockDTOResponse(
                    stock.getTicker(),
                    stock.getCompanyName(),
                    stock.getSector().getDescription(),
                    stock.getNoOfShares(),
                    stock.getPurchasePrice(),
                    stock.getCurrentPrice(),
                    stock.getCurrentReturn(),
                    stock.getCurrentTotalReturn(),
                    stock.getCurrentTotal(),
                    stock.getBuyDate()
            ));
        }
        return allStocksDTO;
    }

    public List<StockDTOResponse> getStockBy(String ticker, String sector) {
        List<Stock> stocksBy = stocksRepository.getStocksBy(ticker, sector);
        List<StockDTOResponse> stocksByDTO = new ArrayList<>();
        for (Stock stock : stocksBy) {
            stocksByDTO.add(new StockDTOResponse(
                    stock.getTicker(),
                    stock.getCompanyName(),
                    stock.getSector().getDescription(),
                    stock.getNoOfShares(),
                    stock.getPurchasePrice(),
                    stock.getCurrentPrice(),
                    stock.getCurrentReturn(),
                    stock.getCurrentTotalReturn(),
                    stock.getCurrentTotal(),
                    stock.getBuyDate()
            ));
        }

        return stocksByDTO;
    }

    public String removeStock(String ticker) {
        return stocksRepository.removeStock(ticker);
    }

    public StockDTOResponse addStock(StockDTORequest stockToAdd) {
        Double currentReturnTotal = (stockToAdd.getCurrentPrice() - stockToAdd.getPurchasePrice()) * stockToAdd.getNoOfShares();
        Double currentReturn = stockToAdd.getCurrentPrice() - stockToAdd.getPurchasePrice();
        Double currentTotal = stockToAdd.getCurrentPrice() * stockToAdd.getNoOfShares();
        Stock stock = new Stock(
                stockToAdd.getTicker(),
                stockToAdd.getCompanyName(),
                Sectors.fromDescription(stockToAdd.getSector()),
                stockToAdd.getNoOfShares(),
                stockToAdd.getPurchasePrice(),
                stockToAdd.getCurrentPrice(),
                BigDecimal.valueOf(currentReturn).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(currentReturnTotal).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(currentTotal).setScale(2, RoundingMode.HALF_UP),
                stockToAdd.getBuyDate()
        );
        logger.info("Stock {} successfully added to the Database", stock.getTicker());
        return new StockDTOResponse(
                stock.getTicker(),
                stock.getCompanyName(),
                stock.getSector().getDescription(),
                stock.getNoOfShares(),
                stock.getPurchasePrice(),
                stock.getCurrentPrice(),
                stock.getCurrentReturn(),
                stock.getCurrentTotalReturn(),
                stock.getCurrentTotal(),
                stock.getBuyDate()
        );

    }

    public List<StockDTOResponse> addStocks(List<StockDTORequest> stocksDTORequest) {
        List<StockDTOResponse> addedStocks = new ArrayList<>();
        for (StockDTORequest stockDTORequest : stocksDTORequest) {
            Double currentReturnTotal = (stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice()) * stockDTORequest.getNoOfShares();
            Double currentReturn = stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice();
            Double currentTotal = stockDTORequest.getCurrentPrice() * stockDTORequest.getNoOfShares();
            Stock stock = new Stock(
                    stockDTORequest.getTicker(),
                    stockDTORequest.getCompanyName(),
                    Sectors.fromDescription(stockDTORequest.getSector()),
                    stockDTORequest.getNoOfShares(),
                    stockDTORequest.getPurchasePrice(),
                    stockDTORequest.getCurrentPrice(),
                    BigDecimal.valueOf(currentReturn).setScale(2, RoundingMode.HALF_UP),
                    BigDecimal.valueOf(currentReturnTotal).setScale(2, RoundingMode.HALF_UP),
                    BigDecimal.valueOf(currentTotal).setScale(2, RoundingMode.HALF_UP),
                    stockDTORequest.getBuyDate()
            );
            stocksRepository.getAllStocks().add(stock);
            addedStocks.add(new StockDTOResponse(
                    stock.getTicker(),
                    stock.getCompanyName(),
                    stock.getSector().getDescription(),
                    stock.getNoOfShares(),
                    stock.getPurchasePrice(),
                    stock.getCurrentPrice(),
                    stock.getCurrentReturn(),
                    stock.getCurrentTotalReturn(),
                    stock.getCurrentTotal(),
                    stockDTORequest.getBuyDate()
            ));
        }
        logger.info("Stocks {} added to the database", stocksDTORequest);
        return addedStocks;
    }


    public StockDTOResponse updateSotck(String ticker, StockDTORequest stockDTORequest) {

        Double currentReturnTotal = (stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice()) * stockDTORequest.getNoOfShares();
        Double currentReturn = stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice();
        Double currentTotal = stockDTORequest.getCurrentPrice() * stockDTORequest.getNoOfShares();
        for (Stock stock : stocksRepository.getAllStocks()) {
            if (stock.getTicker().equalsIgnoreCase(ticker)) {
                stock.setTicker(stockDTORequest.getTicker());
                stock.setCompanyName(stockDTORequest.getCompanyName());
                stock.setSector(Sectors.fromDescription(stockDTORequest.getSector()));
                stock.setNoOfShares(stockDTORequest.getNoOfShares());
                stock.setPurchasePrice(stockDTORequest.getPurchasePrice());
                stock.setCurrentPrice(stockDTORequest.getCurrentPrice());
                stock.setCurrentReturn(BigDecimal.valueOf(currentReturn).setScale(2, RoundingMode.HALF_UP));
                stock.setCurrentTotalReturn(BigDecimal.valueOf(currentReturnTotal).setScale(2, RoundingMode.HALF_UP));
                stock.setCurrentTotal(BigDecimal.valueOf(currentTotal).setScale(2, RoundingMode.HALF_UP));
                stock.setBuyDate(stockDTORequest.getBuyDate());
                return new StockDTOResponse(
                        stock.getTicker(),
                        stock.getCompanyName(),
                        stock.getSector().getDescription(),
                        stock.getNoOfShares(),
                        stock.getPurchasePrice(),
                        stock.getCurrentPrice(),
                        stock.getCurrentReturn(),
                        stock.getCurrentTotalReturn(),
                        stock.getCurrentTotal(),
                        stock.getBuyDate()
                );
            }
        }
        logger.error("Ticker {} not found in the database", ticker);
        throw new NotFoundException("Ticker " + ticker + " not found in the database");
    }

    public StockDTOResponse patchStock(String ticker, StockDTOPatch stockDTOPatch) {
        for (Stock stock : stocksRepository.getAllStocks()) {
            if (stock.getTicker().equalsIgnoreCase(ticker)) {
                stock.setTicker(stockDTOPatch.getTicker() != null ? stockDTOPatch.getTicker() : stock.getTicker());
                stock.setCompanyName(stockDTOPatch.getCompanyName() != null ? stockDTOPatch.getCompanyName() : stock.getCompanyName());
                stock.setSector(stockDTOPatch.getSector() != null ? Sectors.fromDescription(stockDTOPatch.getSector()) : stock.getSector());
                stock.setPurchasePrice(stockDTOPatch.getPurchasePrice() != null ? stockDTOPatch.getPurchasePrice() : stock.getPurchasePrice());
                stock.setCurrentPrice(stockDTOPatch.getCurrentPrice() != null ? stockDTOPatch.getCurrentPrice() : stock.getCurrentPrice());
                stock.setCurrentReturn(
                        stockDTOPatch.getCurrentPrice() != null &&
                                stockDTOPatch.getPurchasePrice() != null ?
                                BigDecimal.valueOf(stockDTOPatch.getCurrentPrice() - stockDTOPatch.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP) :
                                stock.getCurrentReturn()
                );
                stock.setCurrentTotalReturn(
                        stockDTOPatch.getCurrentPrice() != null &&
                                stockDTOPatch.getPurchasePrice() != null &&
                                stockDTOPatch.getNoOfShares() != null ?
                                BigDecimal.valueOf((stockDTOPatch.getCurrentPrice() - stockDTOPatch.getPurchasePrice()) * stockDTOPatch.getNoOfShares()).setScale(2, RoundingMode.HALF_UP) :
                                stock.getCurrentTotalReturn()
                );
                stock.setCurrentTotal(
                        stockDTOPatch.getCurrentPrice() != null &&
                                stock.getNoOfShares() != null ?
                                BigDecimal.valueOf(stockDTOPatch.getCurrentPrice() * stockDTOPatch.getNoOfShares()).setScale(2, RoundingMode.HALF_UP) :
                                stock.getCurrentTotal()
                );
                stock.setBuyDate(
                        stockDTOPatch.getBuyDate() != null ?
                                stockDTOPatch.getBuyDate() :
                                stock.getBuyDate()
                );
                return new StockDTOResponse(
                        stock.getTicker(),
                        stock.getCompanyName(),
                        stock.getSector().getDescription(),
                        stock.getNoOfShares(),
                        stock.getPurchasePrice(),
                        stock.getCurrentPrice(),
                        stock.getCurrentReturn(),
                        stock.getCurrentTotalReturn(),
                        stock.getCurrentTotal(),
                        stock.getBuyDate()
                );
            }
        }
        throw new NotFoundException("Ticker " + ticker + " not found in the database");
    }
}
