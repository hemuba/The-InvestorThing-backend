package com.theinvestorthing.backend.old;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class StockDTORequest_OLD {

    @NotBlank(message = "Ticker cannot be empty.")
    private String ticker;
    private String companyName;
    private String sector;
    @NotNull(message = "Number of shares cannot be empty")
    private Double noOfShares;
    @NotNull(message = "Purchase price cannot be empty")
    private Double purchasePrice;
    @NotNull(message = "Current price cannot be empty")
    private Double currentPrice;
    private LocalDate buyDate;

    public StockDTORequest_OLD() {
    }

    public StockDTORequest_OLD(String ticker, String companyName, String sector, Double noOfShares, Double purchasePrice, Double currentPrice, LocalDate buyDate) {
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
        return this.companyName;
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

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }
}
