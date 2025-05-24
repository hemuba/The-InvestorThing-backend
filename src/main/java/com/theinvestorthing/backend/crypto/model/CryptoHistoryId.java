package com.theinvestorthing.backend.crypto.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class CryptoHistoryId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String symbol;

    private LocalDate data;

    public CryptoHistoryId() {
    }

    public CryptoHistoryId(String symbol, LocalDate data) {
        this.symbol = symbol;
        this.data = data;
    }


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CryptoHistoryId that)) return false;
        return Objects.equals(symbol, that.symbol) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, data);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
