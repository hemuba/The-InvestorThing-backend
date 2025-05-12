package com.stockmanager.backend.dto;

import com.stockmanager.backend.model.MyStock;
import com.stockmanager.backend.model.Stock;

import java.math.RoundingMode;

public class StockMapper {


    public static Stock toStockEntity(StockDTORequest req){
        return new Stock(
                req.getTicker(),
                req.getCompanyName(),
                req.getExchange(),
                req.getSector(),
                req.getCurrency()
        );
    }

    public static StockDTOResponse toStockResponse(Stock req){
        return new StockDTOResponse(
                req.getTicker(),
                req.getCompanyName(),
                req.getExchange(),
                req.getSector(),
                req.getCurrency()
        );
    }

    public static MyStock toMyStockEntity(MyStockDTORequest req){
        return new MyStock(
                req.getTicker(),
                req.getBuyDate(),
                req.getNoOfShares().setScale(2, RoundingMode.HALF_UP),
                req.getPurchasePrice().setScale(2, RoundingMode.HALF_UP),
                req.getCurrentPrice().setScale(2, RoundingMode.HALF_UP)
        );
    }

    public static MyStockDTOResponse toMyStockDTOResponse(MyStock req){
        return new MyStockDTOResponse(
                req.getTicker(),
                req.getBuyDate(),
                req.getNoOfShares().setScale(2, RoundingMode.HALF_UP),
                req.getPurchasePrice().setScale(2, RoundingMode.HALF_UP),
                req.getCurrentPrice().setScale(2, RoundingMode.HALF_UP)
        );
    }
}
