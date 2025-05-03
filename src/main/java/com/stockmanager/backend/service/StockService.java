package com.stockmanager.backend.service;


import com.stockmanager.backend.dto.StockDTORequest;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.model.Sectors;
import com.stockmanager.backend.model.Stock;
import com.stockmanager.backend.repository.StocksRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    private final StocksRepository stocksRepository;

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

    public List<StockDTOResponse> getStockBy(String ticker, String sector){
        List<Stock> stocksBy = stocksRepository.getStocksBy(ticker, sector);
        List<StockDTOResponse> stocksByDTO = new ArrayList<>();
        for (Stock stock : stocksBy){
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

    public String removeStock(String ticker){
        return stocksRepository.removeStock(ticker);
    }

    public String addStocks(StockDTORequest stockToAdd){
        Double currentTotalReturn = (stockToAdd.getCurrentPrice() - stockToAdd.getPurchasePrice()) * stockToAdd.getNoOfShares();
        Stock stock = new Stock(
                stockToAdd.getTicker(),
                stockToAdd.getCompanyName(),
                Sectors.fromDescription(stockToAdd.getSector()),
                stockToAdd.getNoOfShares(),
                stockToAdd.getPurchasePrice(),
                stockToAdd.getCurrentPrice(),
                stockToAdd.getCurrentPrice() - stockToAdd.getPurchasePrice(),
                currentTotalReturn,
                stockToAdd.getCurrentPrice() * stockToAdd.getNoOfShares(),
                stockToAdd.getBuyDate()
        );
        return "Stock " + stock.getTicker() + " succesfully added to the Database";
    }
}
