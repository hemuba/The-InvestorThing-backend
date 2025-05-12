package com.stockmanager.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MyStockDTORequest {

    @NotBlank(message = "Ticker cannot be blank")
    private String ticker;

    @NotNull(message = "Buy date cannot be null")
    private LocalDate buyDate;

    @NotNull(message = "Number of shares cannot be null")
    private BigDecimal noOfShares;

    @NotNull(message = "Purchase price cannot be null")
    private BigDecimal purchasePrice;

    @NotNull(message = "Current price cannot be null")
    private BigDecimal currentPrice;

    public MyStockDTORequest() {
    }

    public MyStockDTORequest(String ticker, LocalDate buyDate, BigDecimal noOfShares, BigDecimal purchasePrice, BigDecimal currentPrice) {
        this.ticker = ticker;
        this.buyDate = buyDate;
        this.noOfShares = noOfShares;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
    }

    public @NotBlank(message = "Ticker cannot be blank") String getTicker() {
        return ticker;
    }

    public void setTicker(@NotBlank(message = "Ticker cannot be blank") String ticker) {
        this.ticker = ticker;
    }

    public @NotNull(message = "Buy date cannot be null") LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(@NotNull(message = "Buy date cannot be null") LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public @NotNull(message = "Number of shares cannot be null") BigDecimal getNoOfShares() {
        return noOfShares;
    }

    public void setNoOfShares(@NotNull(message = "Number of shares cannot be null") BigDecimal noOfShares) {
        this.noOfShares = noOfShares;
    }

    public @NotNull(message = "Purchase price cannot be null") BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(@NotNull(message = "Purchase price cannot be null") BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public @NotNull(message = "Current price cannot be null") BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(@NotNull(message = "Current price cannot be null") BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
