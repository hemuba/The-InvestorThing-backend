package com.theinvestorthing.backend.crypto.service;


import com.theinvestorthing.backend.common.exception.BadRequestException;
import com.theinvestorthing.backend.common.exception.NotFoundException;
import com.theinvestorthing.backend.crypto.dto.CryptoDTOReq;
import com.theinvestorthing.backend.crypto.dto.CryptoDTOResp;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOReq;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOResp;
import com.theinvestorthing.backend.crypto.mapper.CryptoMapper;
import com.theinvestorthing.backend.crypto.model.Crypto;
import com.theinvestorthing.backend.crypto.model.MyCrypto;
import com.theinvestorthing.backend.crypto.repository.CryptoRepository;
import com.theinvestorthing.backend.crypto.repository.MyCryptoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;
    private final MyCryptoRepository myCryptoRepository;

    public CryptoService(CryptoRepository cryptoRepository, MyCryptoRepository myCryptoRepository) {
        this.cryptoRepository = cryptoRepository;
        this.myCryptoRepository = myCryptoRepository;
    }

    // GET Methods from CRYPTO Table
    public List<CryptoDTOResp> getAllCrypto() {
        return cryptoRepository.findAll().stream()
                .map(CryptoMapper::toResponse)
                .toList();

    }

    public CryptoDTOResp getCryptoBySymbol(String symbol) {
        Crypto crypto = cryptoRepository.findBySymbolIgnoreCase(symbol)
                .orElseThrow(() -> new NotFoundException("Crypto " + symbol.toUpperCase() + " not found in Crypto Repository"));
        return CryptoMapper.toResponse(crypto);

    }

    // GET methods from CURRENT_CRYPTO Table

    public List<MyCryptoDTOResp> getAllMyCrypto(){
        List<MyCryptoDTOResp> allMyCrypto =  myCryptoRepository.findAll().stream()
                .map(CryptoMapper::toMyCryptoResponse)
                .toList();
        if (allMyCrypto.isEmpty()){
            throw new BadRequestException("Your wallet is currently empty!");
        }
        return allMyCrypto;
    }

    public MyCryptoDTOResp getMyCryptoBySymbol(String symbol){
        MyCrypto myCrypto = myCryptoRepository.findBySymbolIgnoreCase(symbol)
                .orElseThrow( () -> new NotFoundException("Crypto " + symbol.toUpperCase() + " not found in your wallet."));
        return CryptoMapper.toMyCryptoResponse(myCrypto);
    }

    // POST methods to CURRENT_CRYPTO

    public MyCryptoDTOResp addToMyCrypto(MyCryptoDTOReq req){
        if (cryptoRepository.findBySymbolIgnoreCase(req.getSymbol()).isEmpty()){
            throw new BadRequestException("Crypto " + req.getSymbol().toUpperCase() + " not found in Crypto Repository, hence cannot be added to your wallet.");
        }
        if (myCryptoRepository.findBySymbolIgnoreCase(req.getSymbol()).isPresent()){
            throw new BadRequestException("Crypto " + req.getSymbol().toUpperCase() + " is already present in your wallet!.");
        }
        MyCrypto myCrypto = CryptoMapper.toMyCryptoEntity(req);
        myCryptoRepository.save(myCrypto);
        return CryptoMapper.toMyCryptoResponse(myCrypto);
    }
}
