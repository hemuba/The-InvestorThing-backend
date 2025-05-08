package com.stockmanager.backend.mapper;

import com.stockmanager.backend.dto.StockDTOPatch;
import com.stockmanager.backend.dto.StockDTORequest;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.model.Sectors;
import com.stockmanager.backend.model.Stock;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StockMapper {

    public static StockDTOResponse toResponse(Stock stock){
        BigDecimal currentReturn = BigDecimal.valueOf(stock.getCurrentPrice() - stock.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentReturnTotal = BigDecimal.valueOf((stock.getCurrentPrice() - stock.getPurchasePrice()) * stock.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = BigDecimal.valueOf(stock.getCurrentPrice() * stock.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        return new StockDTOResponse(
                stock.getTicker(),
                stock.getCompanyName(),
                stock.getSector().getDescription(),
                stock.getNoOfShares(),
                stock.getPurchasePrice(),
                stock.getCurrentPrice(),
                currentReturn,
                currentReturnTotal,
                currentTotal,
                stock.getBuyDate()
        );
    }

    public static Stock toEntity(StockDTORequest stock){
        BigDecimal currentReturn = BigDecimal.valueOf(stock.getCurrentPrice() - stock.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentReturnTotal = BigDecimal.valueOf((stock.getCurrentPrice() - stock.getPurchasePrice()) * stock.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = BigDecimal.valueOf(stock.getCurrentPrice() * stock.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        return new Stock(stock.getTicker(),
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

    public static void patchStock(Stock stock, StockDTOPatch patch){
        stock.setTicker(patch.getTicker() != null ?
                patch.getTicker() :
                stock.getTicker());
        stock.setCompanyName(patch.getCompanyName() != null ?
                patch.getCompanyName() :
                stock.getCompanyName());
        stock.setSector(patch.getSector() != null ?
                Sectors.fromDescription(patch.getSector()) :
                stock.getSector());
        stock.setNoOfShares(patch.getNoOfShares() != null ?
                patch.getNoOfShares() :
                stock.getNoOfShares());
        stock.setPurchasePrice(patch.getPurchasePrice() != null ?
                patch.getPurchasePrice() :
                stock.getPurchasePrice());
        stock.setCurrentPrice(patch.getCurrentPrice() != null ?
                patch.getCurrentPrice() :
                stock.getCurrentPrice());

        stock.setCurrentReturn(BigDecimal.valueOf(stock.getCurrentPrice() - stock.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP));
        stock.setCurrentReturnTotal(stock.getCurrentReturn().multiply(BigDecimal.valueOf(stock.getNoOfShares()).setScale(2, RoundingMode.HALF_UP)));
        stock.setCurrentTotal(BigDecimal.valueOf(stock.getCurrentPrice() * stock.getNoOfShares()).setScale(2, RoundingMode.HALF_UP));

        stock.setBuyDate(patch.getBuyDate() != null?
                patch.getBuyDate() :
                stock.getBuyDate());

    }

    public static void updateStock(Stock stock, StockDTORequest update){
        BigDecimal currentReturn = BigDecimal.valueOf(stock.getCurrentPrice() - stock.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentReturnTotal = BigDecimal.valueOf((stock.getCurrentPrice() - stock.getPurchasePrice()) * stock.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = BigDecimal.valueOf(stock.getCurrentPrice() * stock.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        stock.setTicker(update.getTicker());
        stock.setCompanyName(update.getCompanyName());
        stock.setSector(Sectors.fromDescription(update.getSector()));
        stock.setNoOfShares(update.getNoOfShares());
        stock.setPurchasePrice(update.getPurchasePrice());
        stock.setCurrentPrice(update.getCurrentPrice());
        stock.setCurrentReturn(currentReturn);
        stock.setCurrentReturnTotal(currentReturnTotal);
        stock.setCurrentTotal(currentTotal);
        stock.setBuyDate(update.getBuyDate());
    }
}
