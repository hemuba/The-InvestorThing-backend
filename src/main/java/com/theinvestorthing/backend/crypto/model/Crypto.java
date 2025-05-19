package com.theinvestorthing.backend.crypto.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CRYPTO", schema = "IM")
public class Crypto {

    @Id
    @Column(name = "SYMBOL")
    private String symbol;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SECTOR")
    private String sector;

    @OneToMany(mappedBy = "id.crypto", fetch = FetchType.LAZY)
    private List<CryptoHistory> hist;

    public Crypto() {
    }

    public Crypto(String symbol, String name, String sector) {
        this.symbol = symbol;
        this.name = name;
        this.sector = sector;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public List<CryptoHistory> getHist() {
        return hist;
    }
}
