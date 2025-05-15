package com.theinvestorthing.backend.etf.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MyEtfDTOReq {

    @NotBlank(message = "Ticker cannot be blank")
    private String ticker;

    @NotNull(message = "Number of shares cannot be null")
    private BigDecimal noOfShares;

    @NotNull(message = "Purchase price cannot be null")
    private BigDecimal purchasePrice;

    @NotNull(message = "Current Price cannot be null")
    private BigDecimal currentPrice;

    private LocalDate buyDate;


    public MyEtfDTOReq() {
    }

    public MyEtfDTOReq(String ticker, BigDecimal noOfShares, BigDecimal purchasePrice, BigDecimal currentPrice, LocalDate buyDate) {
        this.ticker = ticker;
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

    public BigDecimal getNoOfShares() {
        return noOfShares;
    }

    public void setNoOfShares(BigDecimal noOfShares) {
        this.noOfShares = noOfShares;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal puchasePrice) {
        this.purchasePrice = puchasePrice;
    }

    public BigDecimal getCurrentPrice(){
        return this.currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice){
        this.currentPrice = currentPrice;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }
}
