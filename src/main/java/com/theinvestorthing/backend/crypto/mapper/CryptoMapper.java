package com.theinvestorthing.backend.crypto.mapper;

import com.theinvestorthing.backend.common.exception.NotFoundException;
import com.theinvestorthing.backend.crypto.dto.*;
import com.theinvestorthing.backend.crypto.model.Crypto;
import com.theinvestorthing.backend.crypto.model.CryptoHistory;
import com.theinvestorthing.backend.crypto.model.CryptoHistoryId;
import com.theinvestorthing.backend.crypto.model.MyCrypto;
import com.theinvestorthing.backend.crypto.repository.CryptoRepository;
import com.theinvestorthing.backend.crypto.repository.MyCryptoRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CryptoMapper {
    private final CryptoRepository cryptoRepository;

    public CryptoMapper(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

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

    public CryptoHistory toCryptoHistoryEntity(CryptoHistoryDTOReq req){
        Crypto Crypto = cryptoRepository.findBySymbolIgnoreCase(req.getSymbol())
                .orElseThrow(() -> new NotFoundException(("Crypto " + req.getSymbol().toUpperCase() + " not found in your wallet")));

        CryptoHistoryId id = new CryptoHistoryId(Crypto, req.getData());

        return new CryptoHistory(
          id,
          req.getOpenPrice().setScale(2, RoundingMode.HALF_UP),
          req.getHighPrice().setScale(2, RoundingMode.HALF_UP),
          req.getLowPrice().setScale(2, RoundingMode.HALF_UP),
          req.getClosePrice().setScale(2, RoundingMode.HALF_UP),
          req.getVolume().setScale(2, RoundingMode.HALF_UP)
        );
    }

    public CryptoHistoryDTOResp toCryptoHistoryResp(CryptoHistory req){
        return new CryptoHistoryDTOResp(
                req.getId().getData(),
                req.getId().getCrypto().getSymbol(),
                req.getOpenPrice().setScale(2, RoundingMode.HALF_UP),
                req.getHighPrice().setScale(2, RoundingMode.HALF_UP),
                req.getLowPrice().setScale(2, RoundingMode.HALF_UP),
                req.getClosePrice().setScale(2, RoundingMode.HALF_UP),
                req.getVolume().setScale(2, RoundingMode.HALF_UP)
        );
    }

}
