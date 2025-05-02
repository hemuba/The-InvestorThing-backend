package com.stockmanager.backend.controller;


import com.stockmanager.backend.dto.StockDTORequest;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.exception.BadRequestException;
import com.stockmanager.backend.service.StocksService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
    public List<StockDTOResponse> getAllStocks(){
        return stocksService.getAllStocks();
    }

    @GetMapping("/get-stocks-by")
    public List<StockDTOResponse> getStocksBy(@RequestParam(required = false) String ticker,@RequestParam(required = false) String sector){
        if (ticker == null && sector == null){
            throw new BadRequestException("Missing or wrong query parameters.");
        }

        return stocksService.getStockBy(ticker, sector);
    }

    @PostMapping("/add-stock")
    public String addStock(@Valid @RequestBody StockDTORequest stockDTORequest){
        return stocksService.addStock(stockDTORequest);
    }

    @PostMapping("/add-stocks")
    public String addStocks(@RequestBody List<StockDTORequest> stocksDTORequest){
        return stocksService.addStocks(stocksDTORequest);
    }

    @DeleteMapping("/remove-stock/{ticker}")
    public String removeStock(@PathVariable @NotBlank(message = "Ticker cannot be blank.") String ticker){
        return stocksService.removeStock(ticker);
    }

    @PutMapping("/update-stock/{ticker}")
    public String updateStock(@PathVariable @NotBlank(message = "Ticker cannot be blank.") String ticker, @RequestBody StockDTORequest stockDTORequest){
        return stocksService.updateStock(ticker, stockDTORequest);
    }

    @PatchMapping("/patch-stock/{ticker}")
    public String patchStock(
            @PathVariable @NotBlank(message = "Ticker cannot be blank") String ticker,
            @RequestBody StockDTORequest stockDTORequest
                             ){
        return stocksService.patchStock(ticker, stockDTORequest);
    }

}
