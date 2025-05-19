package com.theinvestorthing.backend.crypto.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name= "CRYPTO_HISTORY", schema = "IM")
public class CryptoHistory {

    @EmbeddedId
    private CryptoHistoryId id;

    @Column(name = "OPEN_PRICE")
    private BigDecimal openPrice;

    @Column(name = "HIGH_PRICE")
    private BigDecimal highPrice;

    @Column(name = "LOW_PRICE")
    private BigDecimal lowPrice;

    @Column(name = "CLOSE_PRICE")
    private BigDecimal closePrice;

    @Column(name = "VOLUME")
    private BigDecimal volume;

    public CryptoHistory() {
    }

    public CryptoHistory(CryptoHistoryId id, BigDecimal openPrice, BigDecimal highPrice, BigDecimal lowPrice, BigDecimal closePrice, BigDecimal volume) {
        this.id = id;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.volume = volume;
    }

    public CryptoHistoryId getId() {
        return id;
    }

    public void setId(CryptoHistoryId id) {
        this.id = id;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }
}
