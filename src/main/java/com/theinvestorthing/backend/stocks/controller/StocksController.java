package com.theinvestorthing.backend.stocks.controller;


import com.theinvestorthing.backend.common.response.ApiResponse;
import com.theinvestorthing.backend.stocks.dto.StockDTOResp;
import com.theinvestorthing.backend.stocks.service.StocksService;
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
    public ResponseEntity<ApiResponse<List<StockDTOResp>>> getAllStocks(@RequestHeader("x-trace-id") String traceId){
        List<StockDTOResp> obj = stocksService.getAllStocks();
        return ResponseEntity.status(200).body(new ApiResponse<List<StockDTOResp>>(
                LocalDateTime.now(),
                200,
                "All Stocks",
                obj,
                traceId));
    }

    @GetMapping("/by-ticker")
    public ResponseEntity<ApiResponse<StockDTOResp>> getStockByTicker(
            @RequestHeader("x-trace-id") String traceId,
            @RequestParam String ticker){
        StockDTOResp obj = stocksService.getStockByTicker(ticker);
        return ResponseEntity.status(200).body(new ApiResponse<StockDTOResp>(
                LocalDateTime.now(),
                200,
                "Stock",
                obj,
                traceId
        ));
    }

    @GetMapping("/{sector}")
    public ResponseEntity<ApiResponse<List<StockDTOResp>>> getStocksBySector(
            @RequestHeader("x-trace-id") String traceId,
            @PathVariable String sector){
        List<StockDTOResp> obj = stocksService.getAllStocksBySector(sector);
        return ResponseEntity.status(200).body(new ApiResponse<List<StockDTOResp>>(
                LocalDateTime.now(),
                200,
                "Stocks by Sector " + sector.toUpperCase(),
                obj,
                traceId));
    }




}
