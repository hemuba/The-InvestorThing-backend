package com.theinvestorthing.backend.crypto.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CURRENT_CRYPTO", schema= "IM")

public class MyCrypto {


    @Id
    @Column(name="SYMBOL")
    private String symbol;

    @Column(name = "NO_OF_COINS")
    private BigDecimal noOfCoins;

    @Column(name="PURCHASE_PRICE")
    private BigDecimal purchasePrice;

    @Column(name="CURRENT_PRICE")
    private BigDecimal currentPrice;

    @Column(name ="CURRENT_RETURN")
    private BigDecimal currentReturn;

    @Column(name="CURRENT_TOTAL")
    private BigDecimal currentTotal;

    @Column(name="BUY_DATE")
    private LocalDate buyDate;

    public MyCrypto() {
    }

    public MyCrypto(String symbol, BigDecimal noOfCoins, BigDecimal purchasePrice, BigDecimal currentPrice, BigDecimal currentReturn, BigDecimal currentTotal, LocalDate buyDate) {
        this.symbol = symbol;
        this.noOfCoins = noOfCoins;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
        this.currentReturn = currentReturn;
        this.currentTotal = currentTotal;
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

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }
}
