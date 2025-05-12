package com.stockmanager.backend.service;


import com.stockmanager.backend.dto.MyStockDTOReq;
import com.stockmanager.backend.dto.MyStockDTOResp;
import com.stockmanager.backend.dto.StockDTOResp;
import com.stockmanager.backend.exception.BadRequestException;
import com.stockmanager.backend.exception.NotFoundException;
import com.stockmanager.backend.mapper.StockMapper;
import com.stockmanager.backend.model.MyStock;
import com.stockmanager.backend.repository.MyStocksRepository;
import com.stockmanager.backend.repository.StocksRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.stockmanager.backend.model.Stock;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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

    // DELETE from CURRENT_STOCKS Table
    @Transactional
    public String deleteFromCurrentStocks(String ticker){
       if (myStocksRepository.findByTickerIgnoreCase(ticker).isEmpty()){
        throw new NotFoundException("Ticker " + ticker.toUpperCase() + " not found in your wallet!");
       }
        myStocksRepository.deleteByTickerIgnoreCase(ticker);
        return "Stock " + ticker.toUpperCase() + " removed from your wallet!";
    }


    // POST to CURRENT_STOCKS Table

    public MyStockDTOResp addToMyStocks(MyStockDTOReq stock){
        Set<String> tickersLower = myStocksRepository.findAll().stream()
                .map(s -> s.getTicker().toLowerCase())
                .collect(Collectors.toSet());
        Set<String> stocksTickersLower = stocksRepository.findAll().stream()
                .map(s -> s.getTicker().toLowerCase())
                .collect(Collectors.toSet());
            String tickerLower = stock.getTicker().toLowerCase();
            if (tickersLower.contains(tickerLower)) {
                throw new BadRequestException("Stock " + stock.getTicker().toUpperCase() + " already in your wallet!");
        }   else if (!stocksTickersLower.contains(tickerLower)){
                throw new BadRequestException("Stock " + stock.getTicker().toUpperCase() + " not found in the Stocks repository");
            }
        MyStock myStock = StockMapper.toMyStockEntity(stock);
        myStocksRepository.save(myStock);
        return StockMapper.toMyStockResp(myStock);
    }




}
