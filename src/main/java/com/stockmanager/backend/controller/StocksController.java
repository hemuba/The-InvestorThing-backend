package com.stockmanager.backend.controller;


import com.stockmanager.backend.dto.MyStockDTOResponse;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.response.ApiResponse;
import com.stockmanager.backend.service.StocksService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/stocks")
@Validated
public class StocksController {

    private final StocksService stocksService;

    public StocksController(StocksService stocksService) {
        this.stocksService = stocksService;
    }

    @GetMapping("/all-stocks")
    public ResponseEntity<ApiResponse> getAllStocks(){
        List<StockDTOResponse> allStocks = stocksService.getAllStocks();
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stock", allStocks));
    }

    @GetMapping("/all-stocks/{sector}")
    public ResponseEntity<ApiResponse> getStocksBySector(
            @PathVariable String sector
    ){
        List<StockDTOResponse> stockBySector = stocksService.getStocksBySector(sector);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stock by sector", stockBySector));
    }

    @GetMapping("/all-stocks/by-ticker")
    public ResponseEntity<ApiResponse> getStockByTicker(@RequestParam String ticker){
        StockDTOResponse stockDTOResponse = stocksService.getAllStocksByTicker(ticker);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stock", stockDTOResponse));
    }

    @GetMapping("/current-stocks")
    public ResponseEntity<ApiResponse> getMyStocks(){
        List<MyStockDTOResponse> myStocks = stocksService.getAllCurrentStocks();
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Current Stocks", myStocks));
    }

    @GetMapping("/current-stocks/by-ticker")
    public ResponseEntity<ApiResponse> getMyStocksByTicker(@RequestParam String ticker){
        MyStockDTOResponse currentStock = stocksService.getStockByTicker(ticker);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Bought Stock", currentStock));
    }
}
