package com.stockmanager.backend.service;


import com.stockmanager.backend.dto.MyStockDTOResponse;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.dto.StockMapper;
import com.stockmanager.backend.exception.NotFoundException;
import com.stockmanager.backend.model.MyStock;
import com.stockmanager.backend.model.Stock;
import com.stockmanager.backend.repository.AllStocksRepository;
import com.stockmanager.backend.repository.MyStocksRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StocksService {

    private final MyStocksRepository myStocksRepository;
    private final AllStocksRepository allStocksRepository;

    public StocksService(AllStocksRepository allStocksRepository, MyStocksRepository myStocksRepository) {
        this.allStocksRepository = allStocksRepository;
        this.myStocksRepository = myStocksRepository;
    }

    public List<StockDTOResponse> getAllStocks(){
        List<Stock> allStocks = allStocksRepository.findAll();
        return allStocks.stream()
                .map(StockMapper::toStockResponse)
                .toList();
    }

    public List<StockDTOResponse> getStocksBySector(String sector){
        List<Stock> stocks = allStocksRepository.findBySectorIgnoreCase(sector);
        if (stocks.isEmpty()){
            throw new NotFoundException("Sector not found in the database");
        }
        return stocks.stream()
                .map(StockMapper::toStockResponse)
                .toList();
    }

    public StockDTOResponse getAllStocksByTicker(String ticker){
       Stock stock =  allStocksRepository.findByTickerIgnoreCase(ticker)
               .orElseThrow(() -> new NotFoundException("Stock not found in the Database"));
       return StockMapper.toStockResponse(stock);
    }

    public List<MyStockDTOResponse> getAllCurrentStocks() {
        List<MyStock> myStocks = myStocksRepository.findAll();
        return myStocks.stream()
                .map(StockMapper::toMyStockDTOResponse)
                .toList();
    }

    public MyStockDTOResponse getStockByTicker(String ticker){
      MyStock myStock =  myStocksRepository.findByTickerIgnoreCase(ticker)
              .orElseThrow(() -> new NotFoundException("Stock not found in the database"));
      return StockMapper.toMyStockDTOResponse(myStock);
    }
}
