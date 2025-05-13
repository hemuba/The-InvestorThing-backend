package com.investmate.backend.stocks.mapper;

import com.investmate.backend.stocks.dto.MyStockDTOReq;
import com.investmate.backend.stocks.dto.MyStockDTOResp;
import com.investmate.backend.stocks.dto.StockDTOReq;
import com.investmate.backend.stocks.dto.StockDTOResp;
import com.investmate.backend.stocks.model.MyStock;
import com.investmate.backend.stocks.model.Stock;

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
