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

    @ManyToOne
    @JoinColumn(name = "SYMBOL", referencedColumnName = "SYMBOL")
    private Crypto crypto;

    private LocalDate data;

    public CryptoHistoryId() {
    }

    public CryptoHistoryId(Crypto crypto, LocalDate data) {
        this.crypto = crypto;
        this.data = data;
    }


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }


    public Crypto getCrypto() {
        return crypto;
    }

    public void setCrypto(Crypto crypto) {
        this.crypto = crypto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CryptoHistoryId that)) return false;
        return Objects.equals(crypto, that.crypto) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crypto, data);
    }
}
