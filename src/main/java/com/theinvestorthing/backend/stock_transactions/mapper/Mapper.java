package com.theinvestorthing.backend.stock_transactions.mapper;

import com.theinvestorthing.backend.stock_transactions.dto.TransactionDTOReq;
import com.theinvestorthing.backend.stock_transactions.dto.TransactionDTOResp;
import com.theinvestorthing.backend.stock_transactions.model.StockTransactions;

import java.math.RoundingMode;

public class Mapper {

    public static StockTransactions toEntity(TransactionDTOReq req){
        return new StockTransactions(
                null,
                req.getTicker().toUpperCase(),
                req.getOperationType(),
                req.getQuantity().setScale(2, RoundingMode.HALF_UP),
                req.getAmount().setScale(2, RoundingMode.HALF_UP),
                req.getOperationDate()
        );
    }

    public static TransactionDTOResp toResponse(StockTransactions req){
        return new TransactionDTOResp(
                req.getTicker(),
                req.getOperationType(),
                req.getQuantity().setScale(2, RoundingMode.HALF_UP),
                req.getAmount().setScale(2, RoundingMode.HALF_UP),
                req.getOperationDate()
        );
    }
}
