package com.investmate.backend.old;


import java.math.BigDecimal;
import java.time.LocalDate;

public class StockDTOResponse_OLD {

    private String ticker;
    private String companyName;
    private String sector;
    private Double noOfShares;
    private Double purchasePrice;
    private Double currentPrice;
    private BigDecimal currentReturn;
    private BigDecimal currentReturnTotal;
    private BigDecimal currentTotal;
    private LocalDate buyDate;

    public StockDTOResponse_OLD() {
    }

    public StockDTOResponse_OLD(String ticker, String companyName, String sector, Double noOfShares, Double purchasePrice, Double currentPrice, BigDecimal currentReturn, BigDecimal currentReturnTotal, BigDecimal currentTotal, LocalDate buyDate) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.sector = sector;
        this.noOfShares = noOfShares;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
        this.currentReturn = currentReturn;
        this.currentReturnTotal = currentReturnTotal;
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

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Double getNoOfShares() {
        return noOfShares;
    }

    public void setNoOfShares (Double noOfShares) {
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

    public BigDecimal getCurrentReturn() {
        return currentReturn;
    }

    public void setCurrentReturn(BigDecimal currentReturn) {
        this.currentReturn = currentReturn;
    }

    public BigDecimal getCurrentReturnTotal() {
        return currentReturnTotal;
    }

    public void setCurrentReturnTotal(BigDecimal currentReturnTotal) {
        this.currentReturnTotal = currentReturnTotal;
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
