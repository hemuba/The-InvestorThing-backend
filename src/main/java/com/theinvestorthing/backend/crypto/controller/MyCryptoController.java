package com.theinvestorthing.backend.crypto.controller;


import com.theinvestorthing.backend.common.response.ApiResponse;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOReq;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOResp;
import com.theinvestorthing.backend.crypto.service.CryptoService;
import com.theinvestorthing.backend.crypto.service.MyCryptoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@RequestMapping("/the-investorthing/my-crypto")
public class MyCryptoController {

    private final MyCryptoService myCryptoService;

    public MyCryptoController(MyCryptoService myCryptoService) {
        this.myCryptoService = myCryptoService;
    }

    // GET methods

    @GetMapping()
    public ResponseEntity<ApiResponse<List<MyCryptoDTOResp>>> getAllMyCrypto(){
        List<MyCryptoDTOResp> obj = myCryptoService.getAllMyCrypto();
        return ResponseEntity.status(200).body(new ApiResponse<List<MyCryptoDTOResp>>(LocalDateTime.now(), 200, "Your Crypto Wallet - current Crypto: " + obj.size(), obj));
    }

    @GetMapping("/by-symbol")
    public ResponseEntity<ApiResponse<MyCryptoDTOResp>> getMyCryptoBySymbol(@RequestParam String symbol){
        MyCryptoDTOResp obj = myCryptoService.getMyCryptoBySymbol(symbol);
        return ResponseEntity.status(200).body(new ApiResponse<MyCryptoDTOResp>(LocalDateTime.now(), 200, "Crypto " + symbol.toUpperCase(), obj));
    }

    // POST methods
    @PostMapping("/add-crypto")
    public ResponseEntity<ApiResponse<MyCryptoDTOResp>> addToMyCrypto(
            @Valid @RequestBody MyCryptoDTOReq req
    ){
        MyCryptoDTOResp obj = myCryptoService.addToMyCrypto(req);
        return ResponseEntity.status(201).body(
                new ApiResponse<MyCryptoDTOResp>(LocalDateTime.now(), 201, "Crypto " + req.getSymbol().toUpperCase() + " added to your wallet!", obj));
    }
}
