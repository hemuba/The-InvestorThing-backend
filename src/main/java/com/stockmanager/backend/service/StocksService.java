package com.stockmanager.backend.service;


import com.stockmanager.backend.dto.MyStockDTOResp;
import com.stockmanager.backend.dto.StockDTOResp;
import com.stockmanager.backend.exception.NotFoundException;
import com.stockmanager.backend.mapper.StockMapper;
import com.stockmanager.backend.model.MyStock;
import com.stockmanager.backend.repository.MyStocksRepository;
import com.stockmanager.backend.repository.StocksRepository;
import org.springframework.stereotype.Service;
import com.stockmanager.backend.model.Stock;
import java.util.List;


@Service
public class StocksService {

    private final StocksRepository stocksRepository;
    private final MyStocksRepository myStocksRepository;

    public StocksService(StocksRepository stocksRepository, MyStocksRepository myStocksRepository) {
        this.stocksRepository = stocksRepository;
        this.myStocksRepository = myStocksRepository;
    }

        // GET from STOCKS Table
    public List<StockDTOResp> getAllStocks(){
       return stocksRepository.findAll().stream()
               .map(StockMapper::toStockResp)
               .toList();
    }

    public List<StockDTOResp> getAllStocksBySector(String sector){
        List<Stock> stocks = stocksRepository.findBySectorIgnoreCase(sector);
        if (stocks.isEmpty()){
            throw new NotFoundException("Sector " + sector + " not found in the Database");
        }
        return stocks.stream()
                .map(StockMapper::toStockResp)
                .toList();
    }

    public StockDTOResp getStockByTicker(String ticker){
        Stock stock = stocksRepository.findByTickerIgnoreCase(ticker)
                .orElseThrow( () -> new NotFoundException("Stock " + ticker + " not found in the Database"));
        return StockMapper.toStockResp(stock);
    }


        // GET from CURRENT_STOCKS Table
    public List<MyStockDTOResp> getAllMyStocks(){
        return myStocksRepository.findAll().stream()
                .map(StockMapper::toMyStockResp)
                .toList();
    }

    public MyStockDTOResp getMyStockByTicker(String ticker){
        MyStock stock = myStocksRepository.findByTickerIgnoreCase(ticker)
                .orElseThrow( () -> new NotFoundException("Stock " + ticker + " not found in the Database"));
        return StockMapper.toMyStockResp(stock);
    }



}
