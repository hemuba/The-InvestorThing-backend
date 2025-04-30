package com.stockmanager.backend.model;

import java.time.LocalDate;

public class Stock {

    private String ticker;
    private String companyName;
    private Sectors sector;
    private Double noOfShares;
    private Double purchasePrice;
    private Double currentPrice;
    private Double currentReturn;
    private Double currentReturnTotal;
    private Double currentTotal;
    private LocalDate buyDate;

    public Stock(String ticker, String companyName, Sectors sector, Double noOfShares, Double purchasePrice, Double currentPrice, Double currentReturn, Double currentReturnTotal, Double currentTotal, LocalDate buyDate) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.sector = sector;
        this.noOfShares = noOfShares;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
        this.currentReturn = currentReturn;
        this.currentReturnTotal = currentReturnTotal;
        this.currentTotal = currentTotal;
        this.buyDate = buyDate;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Sectors getSector() {
        return sector;
    }

    public void setSector(Sectors sector) {
        this.sector = sector;
    }

    public Double getNoOfShares() {
        return noOfShares;
    }

    public void setNoOfShares(Double noOfShares) {
        this.noOfShares = noOfShares;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getCurrentReturn() {
        if (currentPrice == null || purchasePrice == null) return null;
        if (currentPrice.isNaN() || purchasePrice.isNaN()) return null;
        return currentPrice - purchasePrice;
    }

    public void setCurrentReturn(Double currentReturn) {
        this.currentReturn = currentReturn;
    }

    public Double getCurrentReturnTotal() {
        Double currentReturn = getCurrentReturn();
        if (noOfShares == null || currentReturn == null) return null;
        if (noOfShares.isNaN() || currentReturn.isNaN()) return null;
        return noOfShares * currentReturn;
    }

    public void setCurrentReturnTotal(Double currentReturnTotal) {
        this.currentReturnTotal = currentReturnTotal;
    }

    public Double getCurrentTotal() {
        if (currentPrice == null || noOfShares == null ) return null;
        if (currentPrice.isNaN() || noOfShares.isNaN()) return null;
        return currentPrice * noOfShares;
    }

    public void setCurrentTotal(Double currentTotal) {
        this.currentTotal = currentTotal;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }
}
