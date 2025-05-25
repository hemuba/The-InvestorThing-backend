package com.theinvestorthing.backend.crypto.controller;


import com.theinvestorthing.backend.common.response.ApiResponse;
import com.theinvestorthing.backend.crypto.dto.CryptoDTOResp;
import com.theinvestorthing.backend.crypto.dto.CryptoHistoryDTOResp;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOReq;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOResp;
import com.theinvestorthing.backend.crypto.service.CryptoService;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@RequestMapping("/the-investorthing/all-crypto")
public class CryptoController {

    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    //GET methods

    @GetMapping()
    public ResponseEntity<ApiResponse<List<CryptoDTOResp>>> getAllCrypto(){
        List<CryptoDTOResp> obj = cryptoService.getAllCrypto();
        return ResponseEntity.status(200).body(new ApiResponse<List<CryptoDTOResp>>(LocalDateTime.now(), 200, "Crypto Repository", obj));
    }

    @GetMapping("/by-symbol")
    public ResponseEntity<ApiResponse<CryptoDTOResp>> getCryptoBySymbol(@RequestParam String symbol){
        CryptoDTOResp obj = cryptoService.getCryptoBySymbol(symbol);
        return ResponseEntity.status(200).body(new ApiResponse<CryptoDTOResp>(LocalDateTime.now(), 200, "Crypto " + symbol.toUpperCase(), obj));
    }



}
