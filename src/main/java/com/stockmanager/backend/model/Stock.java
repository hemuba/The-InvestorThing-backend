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
    private Double currentTotalReturn;
    private Double currentTotal;
    private LocalDate buyDate;

    public Stock(String ticker, String companyName, Sectors sector, Double noOfShares, Double purchasePrice, Double currentPrice, Double currentReturn, Double currentTotalReturn, Double currentTotal, LocalDate buyDate) {
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

    public Double getCurrentReturn(){
        if (this.currentPrice == null && this.purchasePrice == null) return null;
        if (this.currentPrice.isNaN() && this.purchasePrice.isNaN()) return null;
        return this.currentPrice - this.purchasePrice;
    }

    public void setCurrentReturn(Double currentReturn){
        this.currentReturn = currentReturn;
    }

    public Double getCurrentTotalReturn(){
        Double currentReturn = getCurrentReturn();
        if (noOfShares == null && currentReturn == null) return null;
        if (noOfShares.isNaN() && currentReturn.isNaN()) return null;
        return currentReturn * noOfShares;
    }

    public void setCurrentTotalReturn(Double currentTotalReturn){
        this.currentTotalReturn = currentTotalReturn;
    }

    public Double getCurrentTotal(){
        if (noOfShares == null && currentPrice == null) return null;
        if (noOfShares.isNaN() && currentPrice.isNaN()) return null;
        return noOfShares * currentPrice;
    }

    public void setCurrentTotal(Double currentTotal){
        this.currentTotal = currentTotal;
    }

    public LocalDate getBuyDate(){
        return this.buyDate;
    }

    public void setBuyDate(LocalDate buyDate){
        this.buyDate = buyDate;
    }
}
