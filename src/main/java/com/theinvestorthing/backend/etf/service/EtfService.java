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

    // DELETE from ETF Table


    // POST to ETF Table

    public EtfDTOResp addToAllEtf(EtfDTOReq etfDTOReq){
        if (etfRepository.findByTickerIgnoreCase(etfDTOReq.getTicker()).isPresent()){
            throw new BadRequestException("ETF " + etfDTOReq.getTicker().toUpperCase() + " already present in the ETF Repository");
        }
        Etf etf = EtfMapper.toEntity(etfDTOReq);
        etfRepository.save(etf);
        return EtfMapper.toResponse(etf);
    }

    // GET from CURRENT_ETF Table

    public List<MyEtfDTOResp> getAllMyEtf(){
        return myEtfRepository.findAll().stream()
                .map(EtfMapper::toMyEtfResponse)
                .toList();
    }

    public MyEtfDTOResp getMyEtfByTicker(String ticker){
        MyEtf etf = myEtfRepository.findByTickerIgnoreCase(ticker)
                .orElseThrow(() -> new NotFoundException("Ticker " + ticker.toUpperCase() + " not found in your wallet!"));
        return EtfMapper.toMyEtfResponse(etf);

    }

    // POST to CURRENT_ETF Table
    public MyEtfDTOResp addToMyEtf(MyEtfDTOReq req){
        if (etfRepository.findByTickerIgnoreCase(req.getTicker()).isEmpty()){
            throw new BadRequestException("ETF " + req.getTicker().toUpperCase() + " not found in ETF Repository, hence cannot be added to your wallet.");
       }
        if (myEtfRepository.findByTickerIgnoreCase(req.getTicker()).isPresent()){
            throw new BadRequestException("ETF " + req.getTicker().toUpperCase() + " already present in your wallet!" );

       }
       MyEtf etf = EtfMapper.toMyEtfEntity(req);
       myEtfRepository.save(etf);
       return EtfMapper.toMyEtfResponse(etf);
    }

}
