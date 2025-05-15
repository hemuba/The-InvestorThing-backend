package com.theinvestorthing.backend.stocks.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MyStockDTOReq {

    @NotBlank(message = "Ticker cannot be blank")
    private String ticker;

    private LocalDate buyDate;

    @NotNull(message = "Number of shares cannot be null")
    private BigDecimal noOfShares;

    @NotNull(message = "Purchase price cannot be null")
    private BigDecimal purchasePrice;

    @NotNull(message = "Current price cannot be null")
    private BigDecimal currentPrice;



    public MyStockDTOReq() {
    }

    public MyStockDTOReq(String ticker, LocalDate buyDate, BigDecimal noOfShares, BigDecimal purchasePrice, BigDecimal currentPrice) {
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

    public BigDecimal getPurchasePrice(){
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice){
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
