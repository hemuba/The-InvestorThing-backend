package com.theinvestorthing.backend.stock_transactions.service;

import com.theinvestorthing.backend.stock_transactions.dto.TransactionDTOResp;
import com.theinvestorthing.backend.stock_transactions.mapper.Mapper;
import com.theinvestorthing.backend.stock_transactions.repository.TransactionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionDTOResp> getAllTransactions(){
        return transactionRepository.findAll(Sort.by("operationDate").descending()).stream()
                .map(Mapper::toResponse)
                .toList();
    }

    public List<TransactionDTOResp> getTransactionsByTicker(String ticker){
        return transactionRepository.findByTickerIgnoreCase(ticker).stream()
                .map(Mapper::toResponse)
                .toList();
    }
}
