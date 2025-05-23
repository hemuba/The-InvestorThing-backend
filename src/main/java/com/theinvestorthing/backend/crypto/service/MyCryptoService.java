package com.theinvestorthing.backend.crypto.service;


import com.theinvestorthing.backend.common.exception.BadRequestException;
import com.theinvestorthing.backend.common.exception.NotFoundException;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOReq;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOResp;
import com.theinvestorthing.backend.crypto.mapper.CryptoMapper;
import com.theinvestorthing.backend.crypto.model.MyCrypto;
import com.theinvestorthing.backend.crypto.repository.CryptoRepository;
import com.theinvestorthing.backend.crypto.repository.MyCryptoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyCryptoService {
    private final MyCryptoRepository myCryptoRepository;
    private final CryptoRepository cryptoRepository;


    public MyCryptoService(MyCryptoRepository myCryptoRepository, CryptoRepository cryptoRepository) {
        this.myCryptoRepository = myCryptoRepository;
        this.cryptoRepository = cryptoRepository;
    }

    // GET methods

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

    //DELETE methods
    @Transactional
    public String deleteFromCurrentCrypto(String symbol){
        if (myCryptoRepository.findBySymbolIgnoreCase(symbol).isEmpty()){
            throw new NotFoundException("Crypto " + symbol.toUpperCase() + " not found in your wallet.");
        }
        myCryptoRepository.deleteBySymbolIgnoreCase(symbol);
        return "Crypto " + symbol.toUpperCase() + " removed from your wallet.";    }

    // POST methods

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
