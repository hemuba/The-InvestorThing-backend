package com.theinvestorthing.backend.etf.controller;


import com.theinvestorthing.backend.common.response.ApiResponse;
import com.theinvestorthing.backend.etf.dto.MyEtfDTOReq;
import com.theinvestorthing.backend.etf.dto.MyEtfDTOResp;
import com.theinvestorthing.backend.etf.service.MyEtfService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@RequestMapping("/the-investorthing/my-etf")
public class MyEtfController {

    private final MyEtfService myEtfService;

    public MyEtfController(MyEtfService myEtfService) {
        this.myEtfService = myEtfService;
    }

    //GET Methods
    @GetMapping
    public ResponseEntity<ApiResponse<List<MyEtfDTOResp>>> getAllMyEtf(@RequestHeader("x-trace-id") String traceId){
        List<MyEtfDTOResp> obj = myEtfService.getAllMyEtf();
        return ResponseEntity.status(200).body(new ApiResponse<List<MyEtfDTOResp>>(
                LocalDateTime.now(),
                200,
                "Your ETF Wallet - current ETF: " + obj.size(),
                obj,
                traceId));
    }

    @GetMapping("/by-ticker")
    public ResponseEntity<ApiResponse<MyEtfDTOResp>> getMyEtfByTicker(
            @RequestHeader("x-trace-id") String traceId,
            @RequestParam String ticker){
        MyEtfDTOResp obj = myEtfService.getMyEtfByTicker(ticker);
        return ResponseEntity.status(200).body(new ApiResponse<MyEtfDTOResp>(
                LocalDateTime.now(),
                200,
                "ETF " + obj.getTicker(),
                obj,
                traceId));
    }

    // POST Methods

    @PostMapping("/add-etf")
    public ResponseEntity<ApiResponse<MyEtfDTOResp>> addToMyEtf(
            @RequestHeader("x-trace-id") String traceId,
            @Valid @RequestBody MyEtfDTOReq req){
        MyEtfDTOResp obj = myEtfService.addToMyEtf(req);
        return ResponseEntity.status(201).body(new ApiResponse<MyEtfDTOResp>(
                LocalDateTime.now(),
                201,
                "ETF " + obj.getTicker() + " added to your wallet!"
                , obj,
                traceId));
    }
}
