package com.theinvestorthing.backend.crypto.mapper;

import com.theinvestorthing.backend.crypto.dto.CryptoDTOReq;
import com.theinvestorthing.backend.crypto.dto.CryptoDTOResp;
import com.theinvestorthing.backend.crypto.model.Crypto;

public class CryptoMapper {

    public Crypto toEntity(CryptoDTOReq req){
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

}
