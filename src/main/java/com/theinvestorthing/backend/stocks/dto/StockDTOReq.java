package com.theinvestorthing.backend.stocks.dto;

import jakarta.validation.constraints.NotBlank;

public class StockDTOReq {

    @NotBlank(message = "Ticker cannot be blank")
    private String ticker;

    private String companyName;

    private String exchange;

    private String sector;

    private String currency;

    public StockDTOReq() {
    }

    public StockDTOReq(String ticker, String companyName, String exchange, String sector, String currency) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.exchange = exchange;
        this.sector = sector;
        this.currency = currency;
    }

    public @NotBlank(message = "Ticker cannot be blank") String getTicker() {
        return ticker;
    }

    public void setTicker(@NotBlank(message = "Ticker cannot be blank") String ticker) {
        this.ticker = ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
