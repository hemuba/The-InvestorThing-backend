package com.stockmanager.backend.controller;


import com.stockmanager.backend.dto.StockDTOPatch;
import com.stockmanager.backend.dto.StockDTORequest;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.exception.BadRequestException;
import com.stockmanager.backend.response.ApiResponse;
import com.stockmanager.backend.service.StocksService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@RequestMapping("/stocks")
public class StocksController {

    private final StocksService stocksService;

    public StocksController(StocksService stocksService) {
        this.stocksService = stocksService;
    }

                // GET METHODS //

    @GetMapping
    public ResponseEntity<ApiResponse> getAllStocks() {
        List<StockDTOResponse> respObj = stocksService.getAllStocks();
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stocks", respObj));
    }

    @GetMapping("/by")
    public ResponseEntity<ApiResponse> getStocksBy(
            @RequestParam(required = false) String ticker,
            @RequestParam(required = false) String sector
    ) {
        if (ticker == null && sector == null) {
            throw new BadRequestException("Missing or wrong query parameters.");
        }

        List<StockDTOResponse> respObj = stocksService.getStockBy(ticker, sector);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stocks", respObj));
    }


                // POST METHODS //

    @PostMapping
    public ResponseEntity<ApiResponse> addStock(@Valid @RequestBody StockDTORequest stockDTORequest) {
        StockDTOResponse obj = stocksService.addStock(stockDTORequest);
        return ResponseEntity.status(201).body(new ApiResponse(LocalDateTime.now(), 201, "Stock " + stockDTORequest.getTicker() + " successfully added to the database", obj));
    }

    @PostMapping("/batch")
    public ResponseEntity<ApiResponse> addStocks(@Valid @RequestBody List<StockDTORequest> stocksDTORequest) {
        List<StockDTOResponse> obj = stocksService.addStocks(stocksDTORequest);
        return ResponseEntity.status(201).body(new ApiResponse(LocalDateTime.now(), 201, "Stocks successfully added to the Database", obj));
    }


            // DELETE METHOD //

    @DeleteMapping("/{ticker}")
    public ResponseEntity<ApiResponse> removeStock(@PathVariable @NotBlank(message = "Ticker cannot be blank.") String ticker) {
        String respBody = stocksService.removeStock(ticker);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, respBody, stocksService.getAllStocks()));
    }


            // UPDATE METHOD //

    @PutMapping("/{ticker}")
    public ResponseEntity<ApiResponse> updateStock(
            @PathVariable @NotBlank(message = "Ticker cannot be blank.") String ticker,
            @Valid @RequestBody StockDTORequest stockDTORequest
    ) {
        StockDTOResponse obj = stocksService.updateStock(ticker, stockDTORequest);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stock successfully updated", obj));
    }


            // PATCH METHOD //

    @PatchMapping("/{ticker}")
    public ResponseEntity<ApiResponse> patchStock(
            @PathVariable @NotBlank(message = "Ticker cannot be blank") String ticker,
            @RequestBody StockDTOPatch stockDTOPatch
    ) {
        StockDTOResponse obj = stocksService.patchStock(ticker, stockDTOPatch);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stock successfully patched", obj));
    }

}
