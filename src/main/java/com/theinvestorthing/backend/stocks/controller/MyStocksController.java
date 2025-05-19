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
    public ResponseEntity<ApiResponse> getMyStocks(){
        List<MyStockDTOResp> myStocks = myStockService.getAllMyStocks();
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Your current stocks", myStocks));

    }

    @GetMapping("/by-ticker")
    public ResponseEntity<ApiResponse> getMyStockByTicker(@RequestParam String ticker){
        MyStockDTOResp myStock = myStockService.getMyStockByTicker(ticker);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stock " + ticker.toUpperCase(), myStock));
    }

    // DELETE methods

    @DeleteMapping("/{ticker}")
    public ResponseEntity<ApiResponse> deleteFromMyStocks(@PathVariable String ticker){
        String message = myStockService.deleteFromCurrentStocks(ticker);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, message, myStockService.getAllMyStocks()));
    }

    // POST methods

    @PostMapping("/add-stock")
    public ResponseEntity<ApiResponse> addToMyStock(@Valid @RequestBody MyStockDTOReq myStockDTOReq){
        MyStockDTOResp obj = myStockService.addToMyStocks(myStockDTOReq);
        return ResponseEntity.status(201).body(new ApiResponse(LocalDateTime.now(), 201, "Stock " + myStockDTOReq.getTicker() + " added to your wallet!", obj));
    }
}
