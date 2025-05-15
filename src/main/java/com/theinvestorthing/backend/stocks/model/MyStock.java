package com.theinvestorthing.backend.stocks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CURRENT_STOCKS", schema = "IM")
public class MyStock {

    @Id
    @Column(name = "TICKER")
    private String ticker;

    @Column(name = "BUY_DATE")
    private LocalDate buyDate;

    @Column(name = "NO_OF_SHARES")
    private BigDecimal noOfShares;

    @Column(name = "PURCHASE_PRICE")
    private BigDecimal purchasePrice;

    @Column(name = "CURRENT_PRICE")
    private BigDecimal currentPrice;

    @Column(name = "CURRENT_RETURN")
    private BigDecimal currentReturn;

    @Column(name = "CURRENT_TOTAL")
    private BigDecimal currentTotal;

    public MyStock() {
    }

    public MyStock(String ticker, LocalDate buyDate, BigDecimal noOfShares, BigDecimal purchasePrice, BigDecimal currentPrice, BigDecimal currentReturn, BigDecimal currentTotal) {
        this.ticker = ticker;
        this.buyDate = buyDate;
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
