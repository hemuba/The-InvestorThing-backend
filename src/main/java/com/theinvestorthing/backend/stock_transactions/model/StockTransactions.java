package com.theinvestorthing.backend.stock_transactions.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="STOCK_TRANSACTIONS")
public class StockTransactions {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "TICKER")
    private String ticker;

    @Column(name = "OPERATION_TYPE")
    private String operationType;

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "OPERATION_DATE")
    private LocalDate operationDate;

    public StockTransactions() {
    }

    public StockTransactions(Long id, String ticker, String operationType, BigDecimal quantity, BigDecimal amount, LocalDate operationDate) {
        this.id = id;
        this.ticker = ticker;
        this.operationType = operationType;
        this.quantity = quantity;
        this.amount = amount;
        this.operationDate = operationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }
}
