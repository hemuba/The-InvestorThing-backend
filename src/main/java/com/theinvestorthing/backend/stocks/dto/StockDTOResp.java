package com.theinvestorthing.backend.stocks.dto;

public class StockDTOResp {

    private String ticker;

    private String companyName;

    private String exchange;

    private String sector;

    private String currency;

    public StockDTOResp() {
    }

    public StockDTOResp(String ticker, String companyName, String exchange, String sector, String currency) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.exchange = exchange;
        this.sector = sector;
        this.currency = currency;
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
