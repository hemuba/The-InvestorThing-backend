package com.stockmanager.backend.controller;


import com.stockmanager.backend.dto.StockDTOPatch;
import com.stockmanager.backend.dto.StockDTORequest;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.exceptions.BadRequestException;
import com.stockmanager.backend.response.ApiResponse;
import com.stockmanager.backend.service.StockService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @GetMapping("/stocks")
    public ApiResponse getAllStocks(){
        List<StockDTOResponse> allStocks = stockService.getAllStocks();
        return new ApiResponse(LocalDateTime.now(), 200, "All Stocks", allStocks);
    }

    @GetMapping("/stocks-by")
    public ApiResponse getStocksBy(
            @RequestParam(required = false) String ticker,
            @RequestParam(required = false) String sector
    ){
        if (ticker != null && sector != null) {
            List<StockDTOResponse> stocksBy = stockService.getStockBy(ticker, sector);
            return new ApiResponse(LocalDateTime.now(), 200, "Stocks by", stocksBy);
        } else throw new BadRequestException("Missing required query parameters");
    }

    @DeleteMapping("/remove-stock/{ticker}")
    public ApiResponse removeStock(@PathVariable String ticker){
        String message = stockService.removeStock(ticker);
        return new ApiResponse(LocalDateTime.now(), 200, message, stockService.getAllStocks());
    }

    @PostMapping("/add-stock")
    public ApiResponse addStock(@Valid @RequestBody StockDTORequest stockDTORequest){
        StockDTOResponse obj = stockService.addStock(stockDTORequest);
        return new ApiResponse(LocalDateTime.now(),200, "Stock added successfully to the Database", obj);
    }

    @PostMapping("/add-stocks")
    public ApiResponse addStocks(@Valid @RequestBody List<StockDTORequest> stocksDTORequest){
        List<StockDTOResponse> obj = stockService.addStocks(stocksDTORequest);
        return new ApiResponse(LocalDateTime.now(), 200, "Stocks added successfully to the Database", obj);
    }

    @PutMapping("/update-stock/{ticker}")
    public ApiResponse updateStock(
            @PathVariable @NotBlank(message = "Ticker cannot be blank.") String ticker,
            @Valid @RequestBody StockDTORequest stockDTORequest
    ){
        StockDTOResponse obj = stockService.updateSotck(ticker, stockDTORequest);
        return new ApiResponse(LocalDateTime.now(), 200, "Stock successfully updated", obj);
    }

    @PatchMapping("/patch-stock/{ticker}")
    public ApiResponse patchStock(
            @PathVariable @NotBlank(message = "Ticker cannot be blank") String ticker,
            @RequestBody StockDTOPatch stockDTOPatch
            ){
        StockDTOResponse obj = stockService.patchStock(ticker, stockDTOPatch);
        return new ApiResponse(LocalDateTime.now(), 200, "Stock successfully patched", obj);
    }

}


