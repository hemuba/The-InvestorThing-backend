package com.theinvestorthing.backend.crypto.mapper;

import com.theinvestorthing.backend.crypto.dto.CryptoDTOReq;
import com.theinvestorthing.backend.crypto.dto.CryptoDTOResp;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOReq;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOResp;
import com.theinvestorthing.backend.crypto.model.Crypto;
import com.theinvestorthing.backend.crypto.model.MyCrypto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CryptoMapper {

    public static Crypto toMyCryptoEntity(CryptoDTOReq req){
        return new Crypto(
                req.getSymbol(),
                req.getName(),
                req.getSector()
        );
    }

    public static CryptoDTOResp toResponse(Crypto req){
        return new CryptoDTOResp(
                req.getSymbol().toUpperCase(),
                req.getName(),
                req.getSector()
        );
    }

    public static MyCrypto toMyCryptoEntity(MyCryptoDTOReq req){
        BigDecimal currentReturn = req.getCurrentPrice().subtract(req.getPurchasePrice()).multiply(req.getNoOfCoins()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = req.getCurrentPrice().multiply(req.getNoOfCoins()).setScale(2, RoundingMode.HALF_UP);

        return new MyCrypto(
            req.getSymbol(),
            req.getNoOfCoins(),
            req.getPurchasePrice(),
            req.getCurrentPrice(),
            currentReturn,
            currentTotal,
            req.getBuyDate()
        );
    }

    public static MyCryptoDTOResp toMyCryptoResponse(MyCrypto req){

        BigDecimal currentReturn = req.getCurrentPrice().subtract(req.getPurchasePrice()).multiply(req.getNoOfCoins()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = req.getCurrentPrice().multiply(req.getNoOfCoins()).setScale(2, RoundingMode.HALF_UP);

        return new MyCryptoDTOResp(
                req.getSymbol(),
                req.getNoOfCoins(),
                req.getPurchasePrice(),
                req.getCurrentPrice(),
                currentReturn,
                currentTotal,
                req.getBuyDate()
        );
    }

}
