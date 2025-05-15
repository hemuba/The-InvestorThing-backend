package com.theinvestorthing.backend.etf.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CURRENT_ETF", schema = "IM")
public class MyEtf {

    @Id
    @Column(name = "TICKER")
    private String ticker;

    @Column(name = "NO_OF_SHARES")
    private BigDecimal noOFShares;

    @Column(name = "PURCHASE_PRICE")
    private BigDecimal purchasePrice;

    @Column(name = "CURRENT_PRICE")
    private BigDecimal currentPrice;

    @Column (name = "CURRENT_RETURN")
    private BigDecimal currentReturn;

    @Column (name = "CURRENT_TOTAL")
    private BigDecimal currentTotal;

    @Column (name = "BUY_DATE")
    private LocalDate buyDate;

    public MyEtf() {
    }

    public MyEtf(String ticker, BigDecimal noOFShares, BigDecimal purchasePrice, BigDecimal currentPrice, BigDecimal currentReturn, BigDecimal currentTotal, LocalDate buyDate) {
        this.ticker = ticker;
        this.noOFShares = noOFShares;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
        this.currentReturn = currentReturn;
        this.currentTotal = currentTotal;
        this.buyDate = buyDate;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getNoOFShares() {
        return noOFShares;
    }

    public void setNoOFShares(BigDecimal noOFShares) {
        this.noOFShares = noOFShares;
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
