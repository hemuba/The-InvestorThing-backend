package com.stockmanager.backend.mapper;

import com.stockmanager.backend.dto.MyStockDTOReq;
import com.stockmanager.backend.dto.MyStockDTOResp;
import com.stockmanager.backend.dto.StockDTOReq;
import com.stockmanager.backend.dto.StockDTOResp;
import com.stockmanager.backend.model.MyStock;
import com.stockmanager.backend.model.Stock;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StockMapper {

    public static Stock toStockEntity(StockDTOReq req){
        return new Stock(
                req.getTicker(),
                req.getCompanyName(),
                req.getExchange(),
                req.getSector(),
                req.getCurrency()
        );
    }

    public static StockDTOResp toStockResp(Stock req){
        return new StockDTOResp(
                req.getTicker(),
                req.getCompanyName(),
                req.getExchange(),
                req.getSector(),
                req.getCurrency()
        );
    }

    public static MyStock toMyStockEntity(MyStockDTOReq req){
        BigDecimal noOfShares = req.getNoOfShares().setScale(2, RoundingMode.HALF_UP);
        BigDecimal purchasePrice = req.getPurchasePrice().setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentPrice = req.getCurrentPrice().setScale(2, RoundingMode.HALF_UP);

        BigDecimal currentReturn = currentPrice.subtract(purchasePrice).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = currentPrice.multiply(noOfShares).setScale(2, RoundingMode.HALF_UP);

        return new MyStock(
                req.getTicker(),
                req.getBuyDate(),
                noOfShares,
                purchasePrice,
                currentPrice,
                currentReturn,
                currentTotal

        );
    }

    public static MyStockDTOResp toMyStockResp(MyStock req){
        BigDecimal noOfShares = req.getNoOfShares().setScale(2, RoundingMode.HALF_UP);
        BigDecimal purchasePrice = req.getPurchasePrice().setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentPrice = req.getCurrentPrice().setScale(2, RoundingMode.HALF_UP);

        BigDecimal currentReturn = currentPrice.subtract(purchasePrice).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = currentPrice.multiply(noOfShares).setScale(2, RoundingMode.HALF_UP);

        return new MyStockDTOResp(
                req.getTicker(),
                req.getBuyDate(),
                noOfShares,
                purchasePrice,
                currentPrice,
                currentReturn,
                currentTotal

        );
    }
}
