package com.stockmanager.backend.mapper;

import com.stockmanager.backend.dto.*;
import com.stockmanager.backend.model.Etf;
import com.stockmanager.backend.model.MyEtf;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public static MyEtfDTOResp toMyEtfResponse(MyEtf req){
        BigDecimal noOfShares = req.getNoOFShares().setScale(2, RoundingMode.HALF_UP);
        BigDecimal purchasePrice = req.getPurchasePrice().setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentPrice  = req.getCurrentPrice().setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentReturn = req.getCurrentReturn().setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = req.getCurrentTotal().setScale(2, RoundingMode.HALF_UP);

        return new MyEtfDTOResp(
          req.getTicker(),
          noOfShares,
          purchasePrice,
          currentPrice,
          currentReturn,
          currentTotal,
          req.getBuyDate()

        );
    }

    public static MyEtf toMyEtfEntity(MyEtfDTOReq req){
        BigDecimal noOfShares = req.getNoOfShares().setScale(2, RoundingMode.HALF_UP);
        BigDecimal purchasePrice = req.getPurchasePrice().setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentPrice = req.getCurrentPrice().setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentReturn = currentPrice.subtract(purchasePrice).multiply(noOfShares).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = currentPrice.multiply(noOfShares).setScale(2, RoundingMode.HALF_UP);

        return new MyEtf(
          req.getTicker(),
          noOfShares,
          purchasePrice,
          currentPrice,
          currentReturn,
          currentTotal,
          req.getBuyDate()
        );

    }
}
