package com.stockmanager.backend.service;


import com.stockmanager.backend.dto.StockDTORequest;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.exception.NotFoundException;
import com.stockmanager.backend.model.Sectors;
import com.stockmanager.backend.model.Stock;
import com.stockmanager.backend.repository.CurrentStocks;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StocksService {

    private final CurrentStocks currentStocks;

    public StocksService(CurrentStocks currentStocks) {
        this.currentStocks = currentStocks;
    }

    public List<StockDTOResponse> getAllStocks(){
        List<Stock> allStocks = currentStocks.getAllStocks();
        List<StockDTOResponse> allStockDTO = new ArrayList<>();

        for (Stock stock : allStocks){
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
        return allStockDTO;
    }

    public String addStock(StockDTORequest stockDTORequest){
        Double currentReturnTotal = (stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice()) * stockDTORequest.getNoOfShares();
        currentStocks.getAllStocks().add(new Stock(
                stockDTORequest.getTicker(),
                stockDTORequest.getCompanyName(),
                Sectors.fromDescription(stockDTORequest.getSector()),
                stockDTORequest.getNoOfShares(),
                stockDTORequest.getNoOfShares(),
                stockDTORequest.getPurchasePrice(),
                stockDTORequest.getCurrentPrice(),
                stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice(),
                currentReturnTotal,
                stockDTORequest.getBuyDate()
        ));
        return "Stock " + stockDTORequest.getTicker() + " successfully added to the Database";
    }

    public String removeStock(String ticker){
        return currentStocks.removeStock(ticker);
    }

    public List<StockDTOResponse> getStockBy(String ticker, String settore){
        List<Stock> stocksBy = currentStocks.getStocksBy(ticker, settore);
        List<StockDTOResponse> stocksDTOBy = new ArrayList<>();
        for (Stock stock: stocksBy){
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
        return stocksDTOBy;
    }

    public String updateStock(String ticker, StockDTORequest stockDTORequest){
        if (ticker != null && ! ticker.isEmpty()){
        Double currentReturnTotal = (stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice()) * stockDTORequest.getNoOfShares();
        for (Stock stock : currentStocks.getAllStocks()) {
            if (stock.getTicker().equalsIgnoreCase(ticker)) {
                stock.setTicker(stockDTORequest.getTicker());
                stock.setCompanyName(stockDTORequest.getCompanyName());
                stock.setSector(Sectors.fromDescription(stockDTORequest.getSector()));
                stock.setNoOfShares(stockDTORequest.getNoOfShares());
                stock.setPurchasePrice(stockDTORequest.getPurchasePrice());
                stock.setCurrentPrice(stock.getCurrentPrice());
                stock.setCurrentReturn(stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice());
                stock.setCurrentReturnTotal(currentReturnTotal);
                stock.setCurrentTotal(stockDTORequest.getCurrentPrice() * stockDTORequest.getNoOfShares());
                stock.setBuyDate(stockDTORequest.getBuyDate());
                }
            }
        return "Ticker " + ticker + " update successfully!";
        }

        throw new NotFoundException("Ticker" + ticker + " not found in the Database");
    }

    public String addStocks(List<StockDTORequest> stocksDTORequest){
        for (StockDTORequest stockDTORequest : stocksDTORequest){
            Double currentReturnTotal = (stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice()) * stockDTORequest.getNoOfShares();
            currentStocks.getAllStocks().add(new Stock(
                    stockDTORequest.getTicker(),
                    stockDTORequest.getCompanyName(),
                    Sectors.fromDescription(stockDTORequest.getSector()),
                    stockDTORequest.getNoOfShares(),
                    stockDTORequest.getPurchasePrice(),
                    stockDTORequest.getCurrentPrice(),
                    stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice(),
                    currentReturnTotal,
                    stockDTORequest.getCurrentPrice() * stockDTORequest.getNoOfShares(),
                    stockDTORequest.getBuyDate()
            ));
            }
            return "Stocks successfully added to the Database";
        }

    public String patchStock(String ticker, StockDTORequest stockDTORequest){
        for (Stock stock: currentStocks.getAllStocks()){
            if (stock.getTicker().equalsIgnoreCase(ticker)){
                stock.setTicker(stockDTORequest.getTicker() != null? stockDTORequest.getTicker() : stock.getTicker());
                stock.setCompanyName(stockDTORequest.getCompanyName() != null ? stockDTORequest.getCompanyName() : stock.getCompanyName());
                stock.setSector(stockDTORequest.getSector() != null ? Sectors.fromDescription(stockDTORequest.getSector()) : stock.getSector());
                stock.setPurchasePrice(stockDTORequest.getPurchasePrice() != null ? stockDTORequest.getPurchasePrice() : stock.getPurchasePrice());
                stock.setCurrentPrice(stockDTORequest.getCurrentPrice() != null ? stockDTORequest.getCurrentPrice() : stock.getCurrentPrice());
                stock.setCurrentReturn(stockDTORequest.getCurrentPrice() != null && stockDTORequest.getPurchasePrice() != null ? stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice() : stock.getCurrentReturn());
                stock.setCurrentReturnTotal(
                        stockDTORequest.getCurrentPrice() != null &&
                        stockDTORequest.getPurchasePrice() != null &&
                        stockDTORequest.getNoOfShares() != null ?
                        stockDTORequest.getNoOfShares() * (stockDTORequest.getCurrentPrice() - stockDTORequest.getPurchasePrice()) :
                        stock.getCurrentReturnTotal()
                        );
                stock.setCurrentTotal(
                        stockDTORequest.getCurrentPrice() != null &&
                        stockDTORequest.getNoOfShares() != null ?
                        stockDTORequest.getCurrentPrice() * stockDTORequest.getNoOfShares() :
                        stock.getCurrentTotal()
                );
                stock.setBuyDate(stockDTORequest.getBuyDate() != null ? stockDTORequest.getBuyDate() : stock.getBuyDate());
            }
        }
        return "Stock " + ticker + " successfully updated!";
    }
}
