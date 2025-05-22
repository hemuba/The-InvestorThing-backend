package com.theinvestorthing.backend.crypto.dto;



import java.math.BigDecimal;
import java.time.LocalDate;

public class MyCryptoDTOResp {

    private String symbol;

    private String name;

    private BigDecimal noOfCoins;

    private BigDecimal purchasePrice;

    private BigDecimal currentPrice;

    private BigDecimal currentReturn;

    private BigDecimal currentTotal;

    private LocalDate buyDate;

    public MyCryptoDTOResp(String symbol, String name, BigDecimal noOfCoins, BigDecimal purchasePrice, BigDecimal currentPrice, BigDecimal currentReturn, BigDecimal currentTotal, LocalDate buyDate) {
        this.symbol = symbol;
        this.name = name;
        this.noOfCoins = noOfCoins;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
        this.currentReturn = currentReturn;
        this.currentTotal = currentTotal;
        this.buyDate = buyDate;
    }

    public MyCryptoDTOResp() {
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() { return this.name; }
    public void setName(String name) {this.name = name;}

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
