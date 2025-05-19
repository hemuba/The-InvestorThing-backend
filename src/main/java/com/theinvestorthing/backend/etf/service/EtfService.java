package com.theinvestorthing.backend.etf.service;


import com.theinvestorthing.backend.etf.dto.EtfDTOReq;
import com.theinvestorthing.backend.etf.dto.EtfDTOResp;
import com.theinvestorthing.backend.etf.dto.MyEtfDTOReq;
import com.theinvestorthing.backend.etf.dto.MyEtfDTOResp;
import com.theinvestorthing.backend.common.exception.BadRequestException;
import com.theinvestorthing.backend.common.exception.NotFoundException;
import com.theinvestorthing.backend.etf.mapper.EtfMapper;
import com.theinvestorthing.backend.etf.model.Etf;
import com.theinvestorthing.backend.etf.model.MyEtf;
import com.theinvestorthing.backend.etf.repository.EtfRepository;
import com.theinvestorthing.backend.etf.repository.MyEtfRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtfService {
    private final EtfRepository etfRepository;
    private final MyEtfRepository myEtfRepository;

    public EtfService(EtfRepository etfRepository, MyEtfRepository myEtfRepository) {
        this.etfRepository = etfRepository;
        this.myEtfRepository = myEtfRepository;
    }

        // GET from ETF Table
    public List<EtfDTOResp> getAllEtf(){
        return etfRepository.findAll().stream()
                .map(EtfMapper::toResponse)
                .toList();
    }

    public List<EtfDTOResp> getEtfByTheme(String theme){
        return etfRepository.findByThemeIgnoreCase(theme).stream()
                .map(EtfMapper::toResponse)
                .toList();
    }

    public EtfDTOResp getEtfByTicker(String ticker){
        Etf etf = etfRepository.findByTickerIgnoreCase(ticker)
                .orElseThrow(() -> new NotFoundException("ETF " + ticker.toUpperCase().toUpperCase() + " not found in the ETF repository"));
        return EtfMapper.toResponse(etf);
    }


    // POST methods

    public EtfDTOResp addToAllEtf(EtfDTOReq etfDTOReq) {
        if (etfRepository.findByTickerIgnoreCase(etfDTOReq.getTicker()).isPresent()) {
            throw new BadRequestException("ETF " + etfDTOReq.getTicker().toUpperCase() + " already present in the ETF Repository");
        }
        Etf etf = EtfMapper.toEntity(etfDTOReq);
        etfRepository.save(etf);
        return EtfMapper.toResponse(etf);
    }

}

