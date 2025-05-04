package com.stockmanager.backend.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Stock {

    private String ticker;
    private String companyName;
    private Sectors sector;
    private Double noOfShares;
    private Double purchasePrice;
    private Double currentPrice;
    private BigDecimal currentReturn;
    private BigDecimal currentTotalReturn;
    private BigDecimal currentTotal;
    private LocalDate buyDate;

    public Stock(String ticker, String companyName, Sectors sector, Double noOfShares, Double purchasePrice, Double currentPrice, BigDecimal currentReturn, BigDecimal currentTotalReturn, BigDecimal currentTotal, LocalDate buyDate) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.sector = sector;
        this.noOfShares = noOfShares;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
        this.currentReturn = currentReturn;
        this.currentTotalReturn = currentTotalReturn;
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

    public BigDecimal getCurrentReturn(){
        if (this.currentPrice == null || this.purchasePrice == null) return null;
        if (this.currentPrice.isNaN() || this.purchasePrice.isNaN()) return null;
        return BigDecimal.valueOf(this.currentPrice - this.purchasePrice).setScale(2, RoundingMode.HALF_UP);
    }

    public void setCurrentReturn(BigDecimal currentReturn){
        this.currentReturn = currentReturn;
    }

    public BigDecimal getCurrentTotalReturn(){
        BigDecimal currentReturn = getCurrentReturn();
        if (noOfShares == null || currentReturn == null) return null;
        if (noOfShares.isNaN()) return null;
        return currentReturn.multiply(BigDecimal.valueOf(noOfShares)).setScale(2, RoundingMode.HALF_UP);
    }

    public void setCurrentTotalReturn(BigDecimal currentTotalReturn){
        this.currentTotalReturn = currentTotalReturn;
    }

    public BigDecimal getCurrentTotal(){
        if (noOfShares == null || currentPrice == null) return null;
        if (noOfShares.isNaN() || currentPrice.isNaN()) return null;
        return BigDecimal.valueOf(noOfShares * currentPrice).setScale(2, RoundingMode.HALF_UP);
    }

    public void setCurrentTotal(BigDecimal currentTotal){
        this.currentTotal = currentTotal;
    }

    public LocalDate getBuyDate(){
        return this.buyDate;
    }

    public void setBuyDate(LocalDate buyDate){
        this.buyDate = buyDate;
    }
}
