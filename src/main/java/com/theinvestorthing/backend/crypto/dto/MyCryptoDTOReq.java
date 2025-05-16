package com.theinvestorthing.backend.crypto.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MyCryptoDTOReq {

    @NotBlank(message = "Symbol cannot be blank")
    private String symbol;

    @NotNull(message= "Number of Coins cannot be null")
    private BigDecimal noOfCoins;


    @NotNull(message= "Purchase Price cannot be null")
    private BigDecimal purchasePrice;

    @NotNull(message= "Current Price cannot be null")
    private BigDecimal currentPrice;

    @NotNull(message= "Buy date cannot be null")
    private LocalDate buyDate;

    public MyCryptoDTOReq() {
    }

    public MyCryptoDTOReq(String symbol, BigDecimal noOfCoins, BigDecimal purchasePrice, BigDecimal currentPrice, LocalDate buyDate) {
        this.symbol = symbol;
        this.noOfCoins = noOfCoins;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
        this.buyDate = buyDate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getNoOfCoins() {
        return noOfCoins;
    }

    public void setNoOfCoins(BigDecimal noOfCoins) {
        this.noOfCoins = noOfCoins;
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

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }
}
