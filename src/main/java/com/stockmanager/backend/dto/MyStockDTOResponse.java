package com.stockmanager.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MyStockDTOResponse {

    private String ticker;

    private LocalDate buyDate;

    private BigDecimal noOfShares;

    private BigDecimal purchasePrice;

    private BigDecimal currentPrice;

    public MyStockDTOResponse() {
    }

    public MyStockDTOResponse(String ticker, LocalDate buyDate, BigDecimal noOfShares, BigDecimal purchasePrice, BigDecimal currentPrice) {
        this.ticker = ticker;
        this.buyDate = buyDate;
        this.noOfShares = noOfShares;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public BigDecimal getNoOfShares() {
        return noOfShares;
    }

    public void setNoOfShares(BigDecimal noOfShares) {
        this.noOfShares = noOfShares;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
