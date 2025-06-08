package com.theinvestorthing.backend.stocks.controller;


import com.theinvestorthing.backend.common.response.ApiResponse;
import com.theinvestorthing.backend.stocks.dto.MyStockDTOReq;
import com.theinvestorthing.backend.stocks.dto.MyStockDTOResp;
import com.theinvestorthing.backend.stocks.service.MyStockService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@RequestMapping("/the-investorthing/my-stocks")
public class MyStocksController {

    private final MyStockService myStockService;


    public MyStocksController(MyStockService myStockService) {
        this.myStockService = myStockService;
    }

    // GET methods

    @GetMapping
    public ResponseEntity<ApiResponse<List<MyStockDTOResp>>> getMyStocks(@RequestHeader("x-trace-id") String traceId){
        List<MyStockDTOResp> obj = myStockService.getAllMyStocks();
        return ResponseEntity.status(200).body(new ApiResponse<List<MyStockDTOResp>>(
                LocalDateTime.now(),
                200,
                "Your current stocks",
                obj,
                traceId));

    }

    @GetMapping("/by-ticker")
    public ResponseEntity<ApiResponse<MyStockDTOResp>> getMyStockByTicker(@RequestHeader("x-trace-id") String traceId, @RequestParam String ticker){
        MyStockDTOResp obj = myStockService.getMyStockByTicker(ticker);
        return ResponseEntity.status(200).body(new ApiResponse<MyStockDTOResp>(
                LocalDateTime.now(),
                200,
                "Stock " + ticker.toUpperCase(),
                obj,
                traceId));
    }

    // DELETE methods

    @DeleteMapping("/{ticker}")
    public ResponseEntity<ApiResponse<List<MyStockDTOResp>>> deleteFromMyStocks(@RequestHeader("x-trace-id") String traceId, @PathVariable String ticker){
        String message = myStockService.deleteFromCurrentStocks(ticker);
        return ResponseEntity.status(200).body(new ApiResponse<List<MyStockDTOResp>>(
                LocalDateTime.now(),
                200,
                message,
                myStockService.getAllMyStocks(),
                traceId));
    }

    // POST methods

    @PostMapping("/add-stock")
    public ResponseEntity<ApiResponse<MyStockDTOResp>> addToMyStock(@RequestHeader("x-trace-id") String traceId, @Valid @RequestBody MyStockDTOReq myStockDTOReq){
        MyStockDTOResp obj = myStockService.addToMyStocks(myStockDTOReq);
        return ResponseEntity.status(201).body(new ApiResponse<MyStockDTOResp>(
                LocalDateTime.now(),
                201,
                "Stock " + myStockDTOReq.getTicker() + " added to your wallet!",
                obj,
                traceId));
    }
}
