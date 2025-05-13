package com.stockmanager.backend.mapper;

import com.stockmanager.backend.dto.EtfDTOReq;
import com.stockmanager.backend.dto.EtfDTOResp;
import com.stockmanager.backend.model.Etf;

public class EtfMapper {

    public static EtfDTOResp toResponse(Etf req){
        return new EtfDTOResp(
          req.getTicker(),
          req.getCompanyName(),
          req.getExchange(),
          req.getTheme(),
          req.getCurrency()
        );
    }

    public static Etf toEntity (EtfDTOReq req){
        return new Etf(
          req.getTicker(),
          req.getCompanyName(),
          req.getExchange(),
          req.getTheme(),
          req.getCurrency()
        );
    }
}
