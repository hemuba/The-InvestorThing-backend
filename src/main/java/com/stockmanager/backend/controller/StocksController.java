package com.stockmanager.backend.controller;

import com.stockmanager.backend.dto.*;
import com.stockmanager.backend.model.Etf;
import com.stockmanager.backend.response.ApiResponse;
import com.stockmanager.backend.service.EtfService;
import com.stockmanager.backend.service.StocksService;
import jakarta.validation.Valid;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/investment-manager")
@Validated
public class StocksController {

    private final StocksService stocksService;
    private final EtfService etfService;

    public StocksController(StocksService stocksService, EtfService etfService) {
        this.stocksService = stocksService;
        this.etfService = etfService;
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

        //GET from ETF Table
    @GetMapping("/all-etf")
    public ResponseEntity<ApiResponse> getAllEtf(){
        List<EtfDTOResp> obj = etfService.getAllEtf();
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "All ETF", obj));
    }

    @GetMapping("all-etf/{theme}")
    public ResponseEntity<ApiResponse> getAllEfByTheme(@PathVariable String theme){
        List<EtfDTOResp> obj = etfService.getEtfByTheme(theme);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "ETF by Theme", obj));
    }

    @GetMapping("all-etf/by-ticker")
    public ResponseEntity<ApiResponse> getEtfByTicker(@RequestParam String ticker){
        EtfDTOResp obj = etfService.getEtfByTicker(ticker);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "ETF", obj));
    }

        //POST to ETF Table

    @PostMapping("/all-etf/add-etf")
    public ResponseEntity<ApiResponse> addToAllEf(@Valid @RequestBody EtfDTOReq etfDTOReq){
        EtfDTOResp obj = etfService.addToAllEtf(etfDTOReq);
        return ResponseEntity.status(201).body(new ApiResponse(LocalDateTime.now(), 201, "ETF " + etfDTOReq.getTicker() + " added to the ETF repository", obj));
    }

    //GET from CURRENT_ETF Table

    @GetMapping("/my-etf")
    public ResponseEntity<ApiResponse> getAllMyEtf(){
        List<MyEtfDTOResp> obj = etfService.getAllMyEtf();
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Yor ETF Wallet - current ETF: " + obj.size(), obj));
    }

    @GetMapping("/my-etf/by-ticker")
    public ResponseEntity<ApiResponse> getMyEtfByTicker(@RequestParam String ticker){
        MyEtfDTOResp obj = etfService.getMyEtfByTicker(ticker);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "ETF " + obj.getTicker(), obj));
    }

    @PostMapping("/my-etf/add-etf")
    public ResponseEntity<ApiResponse> addToMyEtf(@Valid @RequestBody MyEtfDTOReq req){
        MyEtfDTOResp obj = etfService.addToMyEtf(req);
        return ResponseEntity.status(201).body(new ApiResponse(LocalDateTime.now(), 201, "ETF " + obj.getTicker() + " added to your wallet!", obj));
    }

}
