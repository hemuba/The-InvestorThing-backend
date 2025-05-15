package com.theinvestorthing.backend.crypto.service;


import com.theinvestorthing.backend.common.exception.NotFoundException;
import com.theinvestorthing.backend.crypto.dto.CryptoDTOResp;
import com.theinvestorthing.backend.crypto.mapper.CryptoMapper;
import com.theinvestorthing.backend.crypto.model.Crypto;
import com.theinvestorthing.backend.crypto.repository.CryptoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;

    public CryptoService(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    public List<CryptoDTOResp> getAllCrypto() {
        return cryptoRepository.findAll().stream()
                .map(CryptoMapper::toResponse)
                .toList();

    }

    public CryptoDTOResp getCryptoByTicker(String id){
        Crypto crypto = cryptoRepository.findByIdIgnoreCase(id)
                .orElseThrow(() -> new NotFoundException("Crypto " + id.toUpperCase() + " not found in Crypto Repository"));
        return CryptoMapper.toResponse(crypto);


    }
}
