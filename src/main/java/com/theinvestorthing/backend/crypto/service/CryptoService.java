package com.theinvestorthing.backend.crypto.service;


import com.theinvestorthing.backend.common.exception.BadRequestException;
import com.theinvestorthing.backend.common.exception.NotFoundException;
import com.theinvestorthing.backend.crypto.dto.*;
import com.theinvestorthing.backend.crypto.mapper.CryptoMapper;
import com.theinvestorthing.backend.crypto.model.Crypto;
import com.theinvestorthing.backend.crypto.model.CryptoHistory;
import com.theinvestorthing.backend.crypto.model.MyCrypto;
import com.theinvestorthing.backend.crypto.repository.CryptoHistoryRepository;
import com.theinvestorthing.backend.crypto.repository.CryptoRepository;
import com.theinvestorthing.backend.crypto.repository.MyCryptoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;


    public CryptoService(CryptoRepository cryptoRepository, MyCryptoRepository myCryptoRepository, CryptoHistoryRepository cryptoHistoryRepository, CryptoMapper cryptoMapper) {
        this.cryptoRepository = cryptoRepository;
    }

    // GET Methods
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


}
