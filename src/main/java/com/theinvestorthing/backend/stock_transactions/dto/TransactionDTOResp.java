package com.theinvestorthing.backend.stock_transactions.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDTOResp {


    private String ticker;

    private String operationType;

    private BigDecimal quantity;

    private BigDecimal amount;

    private LocalDate operationDate;

    public TransactionDTOResp() {
    }

    public TransactionDTOResp(String ticker, String operationType, BigDecimal quantity, BigDecimal amount, LocalDate operationDate) {
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
