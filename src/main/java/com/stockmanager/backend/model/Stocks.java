package com.stockmanager.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STOCKS", schema = "IM")
public class Stocks {

    @Id
    @Column(name= "TICKER")
    private String ticker;

    @Column(name= "COMPANY_NAME")
    private String companyName;

    @Column(name= "EXCHANGE")
    private String exchange;

    @Column(name= "SECTOR")
    private String sector;

    @Column(name= "CURRENCY")
    private String currency;


    public Stocks() {
    }

    public Stocks(String ticker, String companyName, String exchange, String sector, String currency) {
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
