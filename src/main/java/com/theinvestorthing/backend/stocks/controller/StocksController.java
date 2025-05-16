package com.theinvestorthing.backend.stocks.controller;


import com.theinvestorthing.backend.common.response.ApiResponse;
import com.theinvestorthing.backend.stocks.dto.MyStockDTOReq;
import com.theinvestorthing.backend.stocks.dto.MyStockDTOResp;
import com.theinvestorthing.backend.stocks.dto.StockDTOResp;
import com.theinvestorthing.backend.stocks.service.StocksService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@RequestMapping("/the-investorthing/stocks")
public class StocksController {

    private final StocksService stocksService;

    public StocksController(StocksService stocksService) {
        this.stocksService = stocksService;
    }

    // GET from STOCKS table

    @GetMapping("/all-stocks")
    public ResponseEntity<ApiResponse> getAllStocks(){
        List<StockDTOResp> allStocks = stocksService.getAllStocks();
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "All Stocks", allStocks));
    }

    @GetMapping("/all-stocks/by-ticker")
    public ResponseEntity<ApiResponse> getStockByTicker(@RequestParam String ticker){
        StockDTOResp stock = stocksService.getStockByTicker(ticker);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stock", stock));
    }

    @GetMapping("/all-stocks/{sector}")
    public ResponseEntity<ApiResponse> getStocksBySector(@PathVariable String sector){
        List<StockDTOResp> stocks = stocksService.getAllStocksBySector(sector);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stocks by Sector " + sector.toUpperCase(), stocks));
    }

    // GET from CURRENT_STOCKS

    @GetMapping("/my-stocks")
    public ResponseEntity<ApiResponse> getMyStocks(){
        List<MyStockDTOResp> myStocks = stocksService.getAllMyStocks();
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Your current stocks", myStocks));

    }

    @GetMapping("/my-stocks/by-ticker")
    public ResponseEntity<ApiResponse> getMyStockByTicker(@RequestParam String ticker){
        MyStockDTOResp myStock = stocksService.getMyStockByTicker(ticker);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stock " + ticker.toUpperCase(), myStock));
    }

    // DELETE from CURRENT_STOCKS

    @DeleteMapping("/my-stocks/{ticker}")
    public ResponseEntity<ApiResponse> deleteFromMyStocks(@PathVariable String ticker){
        String message = stocksService.deleteFromCurrentStocks(ticker);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, message, stocksService.getAllMyStocks()));
    }

    // POST to CURRENT_STOCKS

    @PostMapping("/my-stocks/add-stock")
    public ResponseEntity<ApiResponse> addToMyStock(@Valid @RequestBody MyStockDTOReq myStockDTOReq){
        MyStockDTOResp obj = stocksService.addToMyStocks(myStockDTOReq);
        return ResponseEntity.status(201).body(new ApiResponse(LocalDateTime.now(), 201, "Stock " + myStockDTOReq.getTicker() + " added to your wallet!", obj));
    }


}
