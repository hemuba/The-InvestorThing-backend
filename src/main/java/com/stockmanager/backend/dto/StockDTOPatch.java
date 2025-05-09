package com.stockmanager.backend.dto;

import java.time.LocalDate;

public class StockDTOPatch {

    private String ticker;
    private String companyName;
    private String sector;
    private Double noOfShares;
    private Double purchasePrice;
    private Double currentPrice;
    private LocalDate buyDate;

    public StockDTOPatch() {
    }

    public StockDTOPatch(String ticker, String companyName, String sector, Double noOfShares, Double purchasePrice, Double currentPrice, LocalDate buyDate) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.sector = sector;
        this.noOfShares = noOfShares;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
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

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
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

    public LocalDate getBuyDate(){
        return this.buyDate;
    }

    public void setBuyDate(LocalDate buyDate){
        this.buyDate = buyDate;
    }
}
