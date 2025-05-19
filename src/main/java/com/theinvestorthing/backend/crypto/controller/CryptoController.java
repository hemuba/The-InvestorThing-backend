package com.theinvestorthing.backend.crypto.controller;


import com.theinvestorthing.backend.common.response.ApiResponse;
import com.theinvestorthing.backend.crypto.dto.CryptoDTOResp;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOReq;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOResp;
import com.theinvestorthing.backend.crypto.service.CryptoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@RequestMapping("/the-investorthing/crypto")
public class CryptoController {

    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    //GET from CRYPTO Table

    @GetMapping("/all-crypto")
    public ResponseEntity<ApiResponse> getAllCrypto(){
        List<CryptoDTOResp> obj = cryptoService.getAllCrypto();
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Crypto Repository", obj));
    }

    @GetMapping("/all-crypto/by-symbol")
    public ResponseEntity<ApiResponse> getCryptoBySymbol(@RequestParam String symbol){
        CryptoDTOResp obj = cryptoService.getCryptoBySymbol(symbol);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Crypto " + symbol.toUpperCase(), obj));
    }

    // GET FROM CURRENT_CRYPTO Table

    @GetMapping("/my-crypto")
    public ResponseEntity<ApiResponse> getAllMyCrypto(){
        List<MyCryptoDTOResp> obj = cryptoService.getAllMyCrypto();
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Your Crypto Wallet - current Crypto: " + obj.size(), obj));
    }

    @GetMapping("/my-crypto/by-symbol")
    public ResponseEntity<ApiResponse> getMyCryptoBySymbol(@RequestParam String symbol){
        MyCryptoDTOResp obj = cryptoService.getMyCryptoBySymbol(symbol);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Crypto " + symbol.toUpperCase(), obj));
    }


    // POST TO CURRENT_CRYPTO
    @PostMapping("/my-crypto/add-crypto")
    public ResponseEntity<ApiResponse> addToMyCrypto(
            @Valid @RequestBody MyCryptoDTOReq req
    ){
        MyCryptoDTOResp obj = cryptoService.addToMyCrypto(req);
        return ResponseEntity.status(201).body(
                new ApiResponse(LocalDateTime.now(), 201, "Crypto " + req.getSymbol().toUpperCase() + " added to your wallet!", obj));
    }
}
