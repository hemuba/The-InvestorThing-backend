package com.theinvestorthing.backend.crypto.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CryptoDTOReq {

    private LocalDate data;

    @NotBlank(message = "Symbol cannot be blank")
    private String symbol;

    private String name;

    private String sector;

    public CryptoDTOReq() {
    }

    public CryptoDTOReq(LocalDate data, String symbol, String name, String sector) {
        this.data =  data;
        this.symbol = symbol;
        this.name = name;
        this.sector = sector;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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
