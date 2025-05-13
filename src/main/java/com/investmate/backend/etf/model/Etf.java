package com.investmate.backend.etf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ETF", schema = "IM")
public class Etf {

    @Id
    @Column(name = "TICKER")
    private String ticker;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "EXCHANGE")
    private String exchange;

    @Column(name = "THEME")
    private String theme;

    @Column(name = "CURRENCY")
    private String currency;

    public Etf() {
    }

    public Etf(String ticker, String companyName, String exchange, String theme, String currency) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.exchange = exchange;
        this.theme = theme;
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
