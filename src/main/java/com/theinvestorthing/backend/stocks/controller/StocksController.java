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

    // GET methods

    @GetMapping
    public ResponseEntity<ApiResponse<List<StockDTOResp>>> getAllStocks(){
        List<StockDTOResp> allStocks = stocksService.getAllStocks();
        return ResponseEntity.status(200).body(new ApiResponse<List<StockDTOResp>>(LocalDateTime.now(), 200, "All Stocks", allStocks));
    }

    @GetMapping("/by-ticker")
    public ResponseEntity<ApiResponse<StockDTOResp>> getStockByTicker(@RequestParam String ticker){
        StockDTOResp stock = stocksService.getStockByTicker(ticker);
        return ResponseEntity.status(200).body(new ApiResponse<StockDTOResp>(LocalDateTime.now(), 200, "Stock", stock));
    }

    @GetMapping("/{sector}")
    public ResponseEntity<ApiResponse<List<StockDTOResp>>> getStocksBySector(@PathVariable String sector){
        List<StockDTOResp> stocks = stocksService.getAllStocksBySector(sector);
        return ResponseEntity.status(200).body(new ApiResponse<List<StockDTOResp>>(LocalDateTime.now(), 200, "Stocks by Sector " + sector.toUpperCase(), stocks));
    }




}
