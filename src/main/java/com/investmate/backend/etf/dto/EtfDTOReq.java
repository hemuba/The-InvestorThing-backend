package com.investmate.backend.etf.dto;

import jakarta.validation.constraints.NotBlank;

public class EtfDTOReq {

    @NotBlank(message = "Ticker cannot be blank")
    private String ticker;

    private String companyName;

    private String exchange;

    private String theme;

    private String currency;

    public EtfDTOReq() {
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
