package com.investmate.backend.old;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StockMapper_OLD {

    public static StockDTOResponse_OLD toResponse(Stock_OLD stock_old){
        BigDecimal currentReturn = BigDecimal.valueOf(stock_old.getCurrentPrice() - stock_old.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentReturnTotal = BigDecimal.valueOf((stock_old.getCurrentPrice() - stock_old.getPurchasePrice()) * stock_old.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = BigDecimal.valueOf(stock_old.getCurrentPrice() * stock_old.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        return new StockDTOResponse_OLD(
                stock_old.getTicker(),
                stock_old.getCompanyName(),
                stock_old.getSector().getDescription(),
                stock_old.getNoOfShares(),
                stock_old.getPurchasePrice(),
                stock_old.getCurrentPrice(),
                currentReturn,
                currentReturnTotal,
                currentTotal,
                stock_old.getBuyDate()
        );
    }

    public static Stock_OLD toEntity(StockDTORequest_OLD stock){
        BigDecimal currentReturn = BigDecimal.valueOf(stock.getCurrentPrice() - stock.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentReturnTotal = BigDecimal.valueOf((stock.getCurrentPrice() - stock.getPurchasePrice()) * stock.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = BigDecimal.valueOf(stock.getCurrentPrice() * stock.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        return new Stock_OLD(stock.getTicker().toUpperCase(),
                stock.getCompanyName(),
                Sectors_OLD.fromDescription(stock.getSector()),
                stock.getNoOfShares(),
                stock.getPurchasePrice(),
                stock.getCurrentPrice(),
                currentReturn,
                currentReturnTotal,
                currentTotal,
                stock.getBuyDate());
    }

    public static void patchStock(Stock_OLD stock, StockDTOPatch_OLD patch){
        stock.setTicker(patch.getTicker() != null ?
                patch.getTicker().toUpperCase() :
                stock.getTicker());
        stock.setCompanyName(patch.getCompanyName() != null ?
                patch.getCompanyName() :
                stock.getCompanyName());
        stock.setSector(patch.getSector() != null ?
                Sectors_OLD.fromDescription(patch.getSector()) :
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

    public static void updateStock(Stock_OLD stock, StockDTORequest_OLD update){
        BigDecimal currentReturn = BigDecimal.valueOf(stock.getCurrentPrice() - stock.getPurchasePrice()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentReturnTotal = BigDecimal.valueOf((stock.getCurrentPrice() - stock.getPurchasePrice()) * stock.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal currentTotal = BigDecimal.valueOf(stock.getCurrentPrice() * stock.getNoOfShares()).setScale(2, RoundingMode.HALF_UP);
        stock.setTicker(update.getTicker().toUpperCase());
        stock.setCompanyName(update.getCompanyName());
        stock.setSector(Sectors_OLD.fromDescription(update.getSector()));
        stock.setNoOfShares(update.getNoOfShares());
        stock.setPurchasePrice(update.getPurchasePrice());
        stock.setCurrentPrice(update.getCurrentPrice());
        stock.setCurrentReturn(currentReturn);
        stock.setCurrentReturnTotal(currentReturnTotal);
        stock.setCurrentTotal(currentTotal);
        stock.setBuyDate(update.getBuyDate());
    }
}
