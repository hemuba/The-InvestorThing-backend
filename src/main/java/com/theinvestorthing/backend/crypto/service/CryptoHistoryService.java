package com.theinvestorthing.backend.crypto.service;


import com.theinvestorthing.backend.crypto.dto.CryptoHistoryDTOResp;
import com.theinvestorthing.backend.crypto.mapper.CryptoMapper;
import com.theinvestorthing.backend.crypto.model.CryptoHistory;
import com.theinvestorthing.backend.crypto.repository.CryptoHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoHistoryService {

    private final CryptoHistoryRepository cryptoHistoryRepository;
    private final CryptoMapper cryptoMapper;

       public CryptoHistoryService(CryptoHistoryRepository cryptoHistoryRepository, CryptoMapper cryptoMapper) {
        this.cryptoHistoryRepository = cryptoHistoryRepository;
        this.cryptoMapper = cryptoMapper;
    }

    // GET methods
    public List<CryptoHistoryDTOResp> getHistoryBySymbol(String symbol){
        List<CryptoHistory>  historyBySymbol = cryptoHistoryRepository.findHistoryBySymbolIgnoreCase(symbol);
        return historyBySymbol.stream()
                .map(cryptoMapper::toCryptoHistoryResp)
                .toList();
    }

}
