package com.theinvestorthing.backend.crypto.controller;


import com.theinvestorthing.backend.common.response.ApiResponse;
import com.theinvestorthing.backend.crypto.dto.CryptoHistoryDTOResp;
import com.theinvestorthing.backend.crypto.service.CryptoHistoryService;
import com.theinvestorthing.backend.crypto.service.CryptoService;
import com.theinvestorthing.backend.crypto.service.MyCryptoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@RequestMapping("/the-investorthing/crypto-history")
public class CryptoHistoryController {

    private final CryptoHistoryService cryptoHistoryService;

    public CryptoHistoryController(CryptoHistoryService cryptoHistoryService) {
        this.cryptoHistoryService = cryptoHistoryService;
    }


    // GET methods
    @GetMapping("/by-symbol")
    public ResponseEntity<ApiResponse<List<CryptoHistoryDTOResp>>> getHistoryBySymbol(@RequestHeader("x-trace-id") String traceId, @RequestParam String symbol){
        List<CryptoHistoryDTOResp> obj = cryptoHistoryService.getHistoryBySymbol(symbol);
        return ResponseEntity.status(200).body(new ApiResponse<List<CryptoHistoryDTOResp>>(
                LocalDateTime.now(),
                200,
                "History for " + symbol.toUpperCase(),
                obj,
                traceId
        ));
    }
}
