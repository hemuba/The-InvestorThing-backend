package com.theinvestorthing.backend.stocks.service;


import com.theinvestorthing.backend.stocks.dto.MyStockDTOReq;
import com.theinvestorthing.backend.stocks.dto.MyStockDTOResp;
import com.theinvestorthing.backend.stocks.dto.StockDTOResp;
import com.theinvestorthing.backend.common.exception.BadRequestException;
import com.theinvestorthing.backend.common.exception.NotFoundException;
import com.theinvestorthing.backend.stocks.mapper.StockMapper;
import com.theinvestorthing.backend.stocks.model.MyStock;
import com.theinvestorthing.backend.stocks.repository.MyStocksRepository;
import com.theinvestorthing.backend.stocks.repository.StocksRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.theinvestorthing.backend.stocks.model.Stock;
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

    public MyStockDTOResp addToMyStocks(MyStockDTOReq stockDTOReq){
        if (stocksRepository.findByTickerIgnoreCase(stockDTOReq.getTicker()).isEmpty()){
            throw new NotFoundException("Stock " + stockDTOReq.getTicker().toUpperCase() + " not found in the Stocks repository, hence cannot be added to your wallet");
        }
        if (myStocksRepository.findByTickerIgnoreCase(stockDTOReq.getTicker()).isPresent()){
            throw new BadRequestException("Stock " + stockDTOReq.getTicker().toUpperCase() + " already present in your Wallet");
        }
        MyStock myStock = StockMapper.toMyStockEntity(stockDTOReq);
        myStocksRepository.save(myStock);
        return StockMapper.toMyStockResp(myStock);
    }




}
