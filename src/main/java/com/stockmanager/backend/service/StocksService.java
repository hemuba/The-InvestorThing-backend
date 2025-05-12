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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<StockDTOResponse> allStocksDTO = new ArrayList<>();
        for (Stock stock : allStocks){
            StockDTOResponse resp = StockMapper.toStockResponse(stock);
            allStocksDTO.add(resp);
        }
        return allStocksDTO;
    }

    public List<StockDTOResponse> getStocksBySector(String sector){
        return Optional.ofNullable(allStocksRepository.findBySectorIgnoreCase(sector))
               .orElseThrow(() -> new NotFoundException("Sector not found in the database"))
                .stream()
                .map(StockMapper::toStockResponse)
                .toList();
    }

    public StockDTOResponse getAllStocksByTicker(String ticker){
       Stock stock =  Optional.ofNullable(allStocksRepository.findByTickerIgnoreCase(ticker))
               .orElseThrow(() -> new NotFoundException("Stock not found in the Database"));
       return StockMapper.toStockResponse(stock);
    }

    public List<MyStockDTOResponse> getAllCurrentStocks() {
        List<MyStock> myStocks = myStocksRepository.findAll();
        List<MyStockDTOResponse> myStockResponse = new ArrayList<>();
        for (MyStock myStock : myStocks) {
            myStockResponse.add(StockMapper.toMyStockDTOResponse(myStock));
        }
        return myStockResponse;
    }

    public MyStockDTOResponse getStockByTicker(String ticker){
      MyStock myStock =  Optional.ofNullable(myStocksRepository.findByTickerIgnoreCase(ticker))
              .orElseThrow(() -> new NotFoundException("Stock not found in the database"));
      return StockMapper.toMyStockDTOResponse(myStock);
    }
}
