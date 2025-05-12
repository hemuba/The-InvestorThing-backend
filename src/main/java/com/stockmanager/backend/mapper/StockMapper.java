package com.stockmanager.backend.mapper;

import com.stockmanager.backend.dto.StockDTOPatch;
import com.stockmanager.backend.dto.StockDTORequest;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.model.Sectors;
import com.stockmanager.backend.old.StockOld;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StockMapper {

    public static StockDTOResponse toResponse(StockOld stockOld){
        BigDecimal currentReturn = BigDecimal.valueOf(stockOld.getCurrentPrice() - stockOld.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentReturnTotal = BigDecimal.valueOf((stockOld.getCurrentPrice() - stockOld.getPurchasePrice()) * stockOld.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = BigDecimal.valueOf(stockOld.getCurrentPrice() * stockOld.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        return new StockDTOResponse(
                stockOld.getTicker(),
                stockOld.getCompanyName(),
                stockOld.getSector().getDescription(),
                stockOld.getNoOfShares(),
                stockOld.getPurchasePrice(),
                stockOld.getCurrentPrice(),
                currentReturn,
                currentReturnTotal,
                currentTotal,
                stockOld.getBuyDate()
        );
    }

    public static StockOld toEntity(StockDTORequest stock){
        BigDecimal currentReturn = BigDecimal.valueOf(stock.getCurrentPrice() - stock.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentReturnTotal = BigDecimal.valueOf((stock.getCurrentPrice() - stock.getPurchasePrice()) * stock.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = BigDecimal.valueOf(stock.getCurrentPrice() * stock.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        return new StockOld(stock.getTicker().toUpperCase(),
                stock.getCompanyName(),
                Sectors.fromDescription(stock.getSector()),
                stock.getNoOfShares(),
                stock.getPurchasePrice(),
                stock.getCurrentPrice(),
                currentReturn,
                currentReturnTotal,
                currentTotal,
                stock.getBuyDate());
    }

    public static void patchStock(StockOld stockOld, StockDTOPatch patch){
        stockOld.setTicker(patch.getTicker() != null ?
                patch.getTicker().toUpperCase() :
                stockOld.getTicker());
        stockOld.setCompanyName(patch.getCompanyName() != null ?
                patch.getCompanyName() :
                stockOld.getCompanyName());
        stockOld.setSector(patch.getSector() != null ?
                Sectors.fromDescription(patch.getSector()) :
                stockOld.getSector());
        stockOld.setNoOfShares(patch.getNoOfShares() != null ?
                patch.getNoOfShares() :
                stockOld.getNoOfShares());
        stockOld.setPurchasePrice(patch.getPurchasePrice() != null ?
                patch.getPurchasePrice() :
                stockOld.getPurchasePrice());
        stockOld.setCurrentPrice(patch.getCurrentPrice() != null ?
                patch.getCurrentPrice() :
                stockOld.getCurrentPrice());

        stockOld.setCurrentReturn(BigDecimal.valueOf(stockOld.getCurrentPrice() - stockOld.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP));
        stockOld.setCurrentReturnTotal(stockOld.getCurrentReturn().multiply(BigDecimal.valueOf(stockOld.getNoOfShares()).setScale(2, RoundingMode.HALF_UP)));
        stockOld.setCurrentTotal(BigDecimal.valueOf(stockOld.getCurrentPrice() * stockOld.getNoOfShares()).setScale(2, RoundingMode.HALF_UP));

        stockOld.setBuyDate(patch.getBuyDate() != null?
                patch.getBuyDate() :
                stockOld.getBuyDate());

    }

    public static void updateStock(StockOld stockOld, StockDTORequest update){
        BigDecimal currentReturn = BigDecimal.valueOf(stockOld.getCurrentPrice() - stockOld.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentReturnTotal = BigDecimal.valueOf((stockOld.getCurrentPrice() - stockOld.getPurchasePrice()) * stockOld.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = BigDecimal.valueOf(stockOld.getCurrentPrice() * stockOld.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        stockOld.setTicker(update.getTicker().toUpperCase());
        stockOld.setCompanyName(update.getCompanyName());
        stockOld.setSector(Sectors.fromDescription(update.getSector()));
        stockOld.setNoOfShares(update.getNoOfShares());
        stockOld.setPurchasePrice(update.getPurchasePrice());
        stockOld.setCurrentPrice(update.getCurrentPrice());
        stockOld.setCurrentReturn(currentReturn);
        stockOld.setCurrentReturnTotal(currentReturnTotal);
        stockOld.setCurrentTotal(currentTotal);
        stockOld.setBuyDate(update.getBuyDate());
    }
}
