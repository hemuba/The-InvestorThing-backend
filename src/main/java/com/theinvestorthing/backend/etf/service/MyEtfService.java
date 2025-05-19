package com.theinvestorthing.backend.etf.service;


import com.theinvestorthing.backend.common.exception.BadRequestException;
import com.theinvestorthing.backend.common.exception.NotFoundException;
import com.theinvestorthing.backend.etf.dto.MyEtfDTOReq;
import com.theinvestorthing.backend.etf.dto.MyEtfDTOResp;
import com.theinvestorthing.backend.etf.mapper.EtfMapper;
import com.theinvestorthing.backend.etf.model.MyEtf;
import com.theinvestorthing.backend.etf.repository.EtfRepository;
import com.theinvestorthing.backend.etf.repository.MyEtfRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyEtfService {

    private final MyEtfRepository myEtfRepository;
    private final EtfRepository etfRepository;

    public MyEtfService(MyEtfRepository myEtfRepository, EtfRepository etfRepository) {
        this.myEtfRepository = myEtfRepository;
        this.etfRepository = etfRepository;
    }


    // GET methods

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

    // POST methods

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
