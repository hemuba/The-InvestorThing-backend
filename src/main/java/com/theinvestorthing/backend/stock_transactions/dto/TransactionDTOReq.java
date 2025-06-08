package com.theinvestorthing.backend.stock_transactions.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDTOReq {


    @NotBlank(message = "Ticker cannot be blank.")
    private String ticker;

    @NotBlank(message = "Operation type cannot be blank.")
    private String operationType;

    @NotNull(message = "Quantity cannot be null.")
    private BigDecimal quantity;

    @NotNull(message = "Amount cannot be null.")
    private BigDecimal amount;

    private LocalDate operationDate;


    public TransactionDTOReq() {
    }

    public TransactionDTOReq(String ticker, String operationType, BigDecimal quantity, BigDecimal amount, LocalDate operationDate) {
        this.ticker = ticker;
        this.operationType = operationType;
        this.quantity = quantity;
        this.amount = amount;
        this.operationDate = operationDate;
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