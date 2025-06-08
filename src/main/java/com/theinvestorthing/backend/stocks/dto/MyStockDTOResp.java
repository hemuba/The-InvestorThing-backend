package com.theinvestorthing.backend.stocks.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MyStockDTOResp {

    private String ticker;

    private String companyName;

    private BigDecimal noOfShares;

    private BigDecimal purchasePrice;

    private BigDecimal currentPrice;

    private BigDecimal currentReturn;

    private BigDecimal currentTotal;

    public MyStockDTOResp() {
    }

    public MyStockDTOResp(String ticker, String companyName, BigDecimal noOfShares, BigDecimal purchasePrice, BigDecimal currentPrice, BigDecimal currentReturn, BigDecimal currentTotal) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.noOfShares = noOfShares;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
        this.currentReturn = currentReturn;
        this.currentTotal = currentTotal;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCompanyName() {return this.companyName; }
    public void setCompanyName(String companyName) {this.companyName = companyName; }

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

    public BigDecimal getCurrentReturn() {
        return currentReturn;
    }

    public void setCurrentReturn(BigDecimal currentReturn) {
        this.currentReturn = currentReturn;
    }

    public BigDecimal getCurrentTotal() {
        return currentTotal;
    }

    public void setCurrentTotal(BigDecimal currentTotal) {
        this.currentTotal = currentTotal;
    }
}
