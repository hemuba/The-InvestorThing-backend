package com.stockmanager.backend.service;


import com.stockmanager.backend.dto.EtfDTOReq;
import com.stockmanager.backend.dto.EtfDTOResp;
import com.stockmanager.backend.exception.BadRequestException;
import com.stockmanager.backend.exception.NotFoundException;
import com.stockmanager.backend.mapper.EtfMapper;
import com.stockmanager.backend.mapper.StockMapper;
import com.stockmanager.backend.model.Etf;
import com.stockmanager.backend.repository.EtfRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EtfService {
    private final EtfRepository eTfRepository;

    public EtfService(EtfRepository etfRepository) {
        this.eTfRepository = etfRepository;
    }

        // GET from ETF Table
    public List<EtfDTOResp> getAllEtf(){
        return eTfRepository.findAll().stream()
                .map(EtfMapper::toResponse)
                .toList();
    }

    public List<EtfDTOResp> getEtfByTheme(String theme){
        return eTfRepository.findByThemeIgnoreCase(theme).stream()
                .map(EtfMapper::toResponse)
                .toList();
    }

    public EtfDTOResp getEtfByTicker(String ticker){
        Etf etf = eTfRepository.findByTickerIgnoreCase(ticker)
                .orElseThrow(() -> new NotFoundException("ETF " + ticker.toUpperCase().toUpperCase() + " not found in the ETF repository"));
        return EtfMapper.toResponse(etf);
    }

    // DELETE from ETF Table


    // POST to ETF Table

    public EtfDTOResp addToAllEtf(EtfDTOReq etfDTOReq){
        if (eTfRepository.findByTickerIgnoreCase(etfDTOReq.getTicker()).isPresent()){
            throw new BadRequestException("ETF " + etfDTOReq.getTicker().toUpperCase() + " already present in the ETF Repository");
        }
        Etf etf = EtfMapper.toEntity(etfDTOReq);
        eTfRepository.save(etf);
        return EtfMapper.toResponse(etf);
    }

}
