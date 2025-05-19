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

    //GET Methods
    @GetMapping
    public ResponseEntity<ApiResponse> getAllEtf(){
        List<EtfDTOResp> obj = etfService.getAllEtf();
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "All ETF", obj));
    }

    @GetMapping("/{theme}")
    public ResponseEntity<ApiResponse> getAllEfByTheme(@PathVariable String theme){
        List<EtfDTOResp> obj = etfService.getEtfByTheme(theme);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "ETF by Theme", obj));
    }

    @GetMapping("/by-ticker")
    public ResponseEntity<ApiResponse> getEtfByTicker(@RequestParam String ticker){
        EtfDTOResp obj = etfService.getEtfByTicker(ticker);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "ETF", obj));
    }

    //POST Methods

    @PostMapping("/add-etf")
    public ResponseEntity<ApiResponse> addToAllEf(@Valid @RequestBody EtfDTOReq etfDTOReq){
        EtfDTOResp obj = etfService.addToAllEtf(etfDTOReq);
        return ResponseEntity.status(201).body(new ApiResponse(LocalDateTime.now(), 201, "ETF " + etfDTOReq.getTicker() + " added to the ETF repository", obj));
    }


}


