package com.theinvestorthing.backend.crypto.dto;

import jakarta.persistence.Column;

public class CryptoDTOResp {

    private String id;

    private String symbol;

    private String name;

    private String sector;

    public CryptoDTOResp() {
    }

    public CryptoDTOResp(String id, String symbol, String name, String sector) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.sector = sector;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
