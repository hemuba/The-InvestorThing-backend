package com.theinvestorthing.backend.etf.controller;


import com.theinvestorthing.backend.common.response.ApiResponse;
import com.theinvestorthing.backend.etf.dto.EtfDTOReq;
import com.theinvestorthing.backend.etf.dto.EtfDTOResp;
import com.theinvestorthing.backend.etf.dto.MyEtfDTOReq;
import com.theinvestorthing.backend.etf.dto.MyEtfDTOResp;
import com.theinvestorthing.backend.etf.service.EtfService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@RequestMapping("/the-investorthing/etf")
public class EtfController {

    private final EtfService etfService;

    public EtfController(EtfService etfService) {
        this.etfService = etfService;
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

    // POST to CURRENT_ETF Table

    @PostMapping("/my-etf/add-etf")
    public ResponseEntity<ApiResponse> addToMyEtf(@Valid @RequestBody MyEtfDTOReq req){
        MyEtfDTOResp obj = etfService.addToMyEtf(req);
        return ResponseEntity.status(201).body(new ApiResponse(LocalDateTime.now(), 201, "ETF " + obj.getTicker() + " added to your wallet!", obj));
    }
}
