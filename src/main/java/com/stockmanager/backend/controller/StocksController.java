package com.stockmanager.backend.controller;


import com.stockmanager.backend.dto.StockDTORequest;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.exception.BadRequestException;
import com.stockmanager.backend.response.ApiResponse;
import com.stockmanager.backend.service.StocksService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class StocksController {

    private final StocksService stocksService;

    public StocksController(StocksService stocksService) {
        this.stocksService = stocksService;
    }

    @GetMapping("/get-stocks")
    public ResponseEntity<ApiResponse> getAllStocks(){
        List<StockDTOResponse> respObj = stocksService.getAllStocks();
        return ResponseEntity.status(200).body(new ApiResponse(200, "Stocks", respObj));
    }

    @GetMapping("/get-stocks-by")
    public ResponseEntity<ApiResponse> getStocksBy(@RequestParam(required = false) String ticker,@RequestParam(required = false) String sector){
        if (ticker == null && sector == null){
            throw new BadRequestException("Missing or wrong query parameters.");
        }
        List<StockDTOResponse> respObj = stocksService.getStockBy(ticker, sector);
        return ResponseEntity.status(201).body(new ApiResponse(201, "Stocks", respObj));
    }

    @PostMapping("/add-stock")
    public ResponseEntity<ApiResponse> addStock(@Valid @RequestBody StockDTORequest stockDTORequest){
        String respBody = stocksService.addStock(stockDTORequest);
        return ResponseEntity.status(201).body(new ApiResponse(201, respBody, null));
    }

    @PostMapping("/add-stocks")
    public ResponseEntity<ApiResponse> addStocks(@RequestBody List<StockDTORequest> stocksDTORequest){
        String respBody = stocksService.addStocks(stocksDTORequest);
        return ResponseEntity.status(201).body(new ApiResponse(201, respBody, null));
    }

    @DeleteMapping("/remove-stock/{ticker}")
    public ResponseEntity<ApiResponse> removeStock(@PathVariable @NotBlank(message = "Ticker cannot be blank.") String ticker){
        String respBody = stocksService.removeStock(ticker);
        return ResponseEntity.status(201).body(new ApiResponse(201, respBody, null));
    }

    @PutMapping("/update-stock/{ticker}")
    public ResponseEntity<ApiResponse> updateStock(@PathVariable @NotBlank(message = "Ticker cannot be blank.") String ticker, @RequestBody StockDTORequest stockDTORequest){
        String respBody = stocksService.updateStock(ticker, stockDTORequest);
        return ResponseEntity.status(201).body(new ApiResponse(201, respBody, null));
    }

    @PatchMapping("/patch-stock/{ticker}")
    public ResponseEntity<ApiResponse> patchStock(
            @PathVariable @NotBlank(message = "Ticker cannot be blank") String ticker,
            @RequestBody StockDTORequest stockDTORequest
                             ){
        String respBody = stocksService.patchStock(ticker, stockDTORequest);
        return ResponseEntity.status(201).body(new ApiResponse(201, respBody, null));
    }

}
